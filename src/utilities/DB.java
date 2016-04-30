/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import datasets.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class DB {

    static Connection c = null;

            // CONFIGURACIÓN DE PROTOCOLO
    // Separadores a utilizar en las migraciones de datos
    final String DATASET_SEPARATOR = "#dataset#";
    final String FIELD_SEPARATOR = "#field#";
    final String ELEMENT_SEPARATOR = "#element#";

    public void connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Opened database successfully");
    }

    public void initializeDB() {
        try {
            PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS main.advices ("
                    + "`id`  INTEGER PRIMARY KEY ,"
                    + "topic_id int(12),"
                    + "question varchar(300) not null,"
                    + "tip varchar(500) not null,"
                    + "tipOnActive number(1)"
                    + ");"
            );

            ps.execute();

            ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS main.multiadvices ("
                    + "`id`  INTEGER PRIMARY KEY ,"
                    + "memberids varchar(200),"
                    + "topic_id int(12),"
                    + "tip varchar(500) not null"
                    + ");"
            );

            ps.execute();

            ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS main.registry ("
                    + "`id`  INTEGER PRIMARY KEY ,"
                    + "topic_id int(12),"
                    + "registry_num int(10) not null,"
                    + "success int(3),"
                    + "description text,"
                    + "tips text"
                    + ");"
            );

            ps.execute();

            ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS main.topics ("
                    + "`id`  INTEGER PRIMARY KEY ,"
                    + "name varchar(80) not null,"
                    + "satisfactionLevels varchar(500),"
                    + "colors varchar(500)"
                    + ");"
            );

            ps.execute();

            ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS main.tipvals ("
                    + "`id`  INTEGER PRIMARY KEY ,"
                    + "topic_id int(10) not null,"
                    + "default_tip text,"
                    + "tiplist text not null,"
                    + "vals text,"
                    + "question text"
                    + ");"
            );

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportTopic(int topic_id, int exportRegistries, String path) {

        BufferedWriter writer = null;
        try {

            // TOPIC HEAD INFO
            Topic topic = getTopic(topic_id);
            File file = new File(path + "/Topic_" + topic.getName());
            ArrayList<Advice> adv = getAdvicesForTopic(topic_id);
            ArrayList<MultiAdvice> mul = getMultiAdvicesForTopic(topic_id);

            writer = new BufferedWriter(new FileWriter(file));

            writer.append(topic.getName())
                    .append(FIELD_SEPARATOR);
            for (int color : topic.getColors()) {                  // Escritura de los colores
                writer.append(String.valueOf(color))
                        .append(ELEMENT_SEPARATOR);
            }
            writer.append(FIELD_SEPARATOR);
            for (String s : topic.getSatisfactionLevels()) {      // Escritura de satisfaction levels
                writer.append(s)
                        .append(ELEMENT_SEPARATOR);
            }
            writer.append(DATASET_SEPARATOR);

            // ADVICES
            for (Advice a : adv) {
                writer.append(a.getQuestion())
                        .append(FIELD_SEPARATOR)
                        .append(a.getTip())
                        .append(FIELD_SEPARATOR)
                        .append(String.valueOf(a.isTipOnActive()))
                        .append(ELEMENT_SEPARATOR);
            }
            writer.append(DATASET_SEPARATOR);

            // MULTIADVICES
            for (MultiAdvice m : mul) {
                writer.append(m.getQuestion())
                        .append(FIELD_SEPARATOR)
                        .append(m.getTip())
                        .append(FIELD_SEPARATOR)
                        .append(m.stringifyMembers())
                        .append(ELEMENT_SEPARATOR);
            }
            writer.append(DATASET_SEPARATOR);

            // REGISTRIES
            if (exportRegistries == 0) {  //Exportamos registros
                ArrayList<Registry> rl = getRegistrosList(topic_id);

                for (Registry reg : rl) {
                    writer.append(reg.getTitle())
                            .append(FIELD_SEPARATOR)
                            .append(reg.getSuccess())
                            .append(FIELD_SEPARATOR)
                            .append(reg.getTips())
                            .append(FIELD_SEPARATOR)
                            .append(reg.getDescription())
                            .append(FIELD_SEPARATOR)
                            .append(String.valueOf(reg.getRegistry_num()))
                            .append(ELEMENT_SEPARATOR);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void importTopic(File file) {

        Topic topic = new Topic();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            String data = sb.toString();
            String[] datasets = data.split(DATASET_SEPARATOR);
         

            // ######### Cabecera de topic
            String[] fields = datasets[0].split(FIELD_SEPARATOR);
            topic.setName(fields[0]);

            String[] temp = fields[1].split(ELEMENT_SEPARATOR);
            int[] colors = new int[temp.length];
            for (int i = 0; i < temp.length; i++) {
                colors[i] = Integer.valueOf(temp[i]);
            }
            topic.setColors(colors);

            topic.setSatisfactionLevels(fields[2].split(ELEMENT_SEPARATOR));

            // ######### Advices
            String[] elements = datasets[1].split(ELEMENT_SEPARATOR);
            ArrayList<Advice> adviceList = new ArrayList<>();

            for (int i = 0; i < elements.length; i++) {
                if (!(elements[i].equals("")) && elements[i] != null) {
                    Advice adv = new Advice();
                    fields = elements[i].split(FIELD_SEPARATOR);
                    adv.setQuestion(fields[0]);
                    adv.setTip(fields[1]);
                    adv.setTipOnActive(Boolean.valueOf(fields[2]));
                    adviceList.add(adv);
                }
            }
            
            //  #######  Multiadvices
            if(datasets.length >= 3){        
            elements = datasets[2].split(ELEMENT_SEPARATOR);
            for (int i = 0; i < elements.length; i++) {
                if (!(elements[i].equals("")) && elements[i] != null) {
                    MultiAdvice multi = new MultiAdvice();
                    fields = elements[i].split(FIELD_SEPARATOR);
                    multi.setQuestion(fields[0]);
                    multi.setTip(fields[1]);
                    multi.setMembersFromString(fields[2]);
                    adviceList.add(multi); 
                }
            }
            }
            // ####### Registries
            int topic_id = addTopic(topic, adviceList);
            if(datasets.length == 4){
            elements = datasets[3].split(ELEMENT_SEPARATOR);
            Registry[]registries = new Registry[elements.length];
            for (int i = 0; i < elements.length; i++) {
                if (!(elements[i].equals("")) && elements[i] != null) {
                    Registry reg = new Registry();
                    fields = elements[i].split(FIELD_SEPARATOR);
                    reg.setTitle(fields[0]);
                    reg.setSuccess(fields[1]);
                    reg.setTips(fields[2]);
                    reg.setDescription(fields[3]);
                    reg.setRegistry_num(Integer.valueOf(fields[4]));
                    registries[i] = reg;
                }
            }
            // ###### Meter en la DB
            
            for(Registry r : registries){
                r.setTopic_id(topic_id);
                insertRegistry(r);
            }
            }
            
        } catch (NumberFormatException ex){
             System.out.println("ERROR DE PARSEO");
        } catch (FileNotFoundException ex) {
            System.out.println("FICHERO NO ENCONTRADO");
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void writeToFile(File file, Object data) {

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);

            if (data instanceof Topic) {
                Topic t = (Topic) data;
                fw.write(t.getName() + "|" + t.stringifySatisfactionLevels() + "|" + t.stringifyColors() + "\n");
            } else if (data instanceof Advice) {
                Advice a = (Advice) data;
                fw.write(a.getQuestion() + "|" + a.getTip() + "|" + a.getTopic_id() + "\n");
            } else if (data instanceof MultiAdvice) {
                MultiAdvice a = (MultiAdvice) data;
                fw.write(a.getQuestion() + "|" + a.getQuestion() + "|" + a.getTopic_id() + "|" + a.stringifyMembers() + "\n");
            } else if (data instanceof AdviceFromValue) {
                AdviceFromValue a = (AdviceFromValue) data;
                fw.write(a.getQuestion() + "|" + a.stringifyVals() + "|" + a.stringifyTips() + "|" + a.getDefaultTip() + "\n");
            }

        } catch (IOException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ResultSet getTopics() {
        ResultSet rs = null;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM topics");
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public void deleteTopic(int topic_id) {

        try {

            PreparedStatement ps = c.prepareStatement("DELETE FROM topics WHERE id=" + topic_id);
            ps.execute();

            ps = c.prepareStatement("DELETE FROM advices WHERE topic_id=" + topic_id);
            ps.execute();

            ps = c.prepareStatement("DELETE FROM multiadvices WHERE topic_id=" + topic_id);
            ps.execute();

            ps = c.prepareStatement("DELETE FROM registry WHERE topic_id=" + topic_id);
            ps.execute();

            ps = c.prepareStatement("DELETE FROM tipvals WHERE topic_id=" + topic_id);
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void resetTopic(int topic_id) {

        try {

            PreparedStatement ps = c.prepareStatement("DELETE FROM registry WHERE topic_id=" + topic_id);
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet getRegistrosForTopic(int topic_id) {
        ResultSet rs = null;
        try {

            PreparedStatement ps = c.prepareStatement("SELECT * FROM registry WHERE topic_id=" + topic_id);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ArrayList<Registry> getRegistrosList(int topic_id) {
        ArrayList<Registry> al = new ArrayList<>();
        try {

            PreparedStatement ps = c.prepareStatement("SELECT * FROM registry WHERE topic_id=" + topic_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Registry reg = new Registry(
                        rs.getInt("topic_id"),
                        rs.getInt("registry_num"),
                        rs.getString("title"),
                        rs.getString("success"),
                        rs.getString("description"),
                        rs.getString("tips"));
                al.add(reg);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public Topic getTopic(int id) {
        Topic topic = null;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM topics WHERE id=" + id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            topic = new Topic();
            topic.setSatisfactionLevels(rs.getString("satisfactionLevels").split("/"));
            String[] colors = rs.getString("colors").split("/");

            int[] realcolors = new int[colors.length];

            for (int i = 0; i < colors.length; i++) {
                String trim = colors[i].trim();
                realcolors[i] = Integer.parseInt(trim);

            }
            topic.setColors(realcolors);
            topic.setName(rs.getString("name"));
            topic.setId(id);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return topic;

    }

    public int addTopic(Topic topic, ArrayList<Advice> advices) {

        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO topics (name, satisfactionLevels, colors) VALUES ('" + topic.getName() + "', '" + topic.stringifySatisfactionLevels() + "', '" + topic.stringifyColors() + "')");
            ps.execute();

            topic.setId(getMaxID("topics"));

            for (Advice adv : advices) {

                if (adv instanceof AdviceFromValue) {
                    AdviceFromValue a = (AdviceFromValue) adv;
                    ps = c.prepareStatement("INSERT INTO tipvals (topic_id, default_tip, tiplist, vals, question) VALUES (" + topic.getId() + ", '" + a.getDefaultTip() + "', '" + a.stringifyTips() + "', '" + a.stringifyVals() + "', '" + a.getQuestion() + "')");
                    ps.execute();
                } else if (adv instanceof MultiAdvice) {
                    MultiAdvice a = (MultiAdvice) adv;
                    ps = c.prepareStatement("INSERT INTO multiadvices (memberids, topic_id, tip) VALUES ('" + a.stringifyMembers() + "', " + topic.getId() + ", '" + a.getTip() + "')");
                    ps.execute();
                } else {
                    ps = c.prepareStatement("INSERT INTO advices (topic_id, question, tip, tipOnActive) VALUES (" + topic.getId() + ", '" + adv.getQuestion() + "', '" + adv.getTip() + "', '"+adv.isTipOnActive()+"')");
                    ps.execute();
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return topic.getId();
    }

    public ArrayList getAdvicesForTopic(int topic_id) {

        ArrayList<Advice> advices = new ArrayList<>();
        advices = getAdvicesFromValueForTopic(topic_id);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM advices WHERE topic_id=" + topic_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                advices.add(new Advice(rs.getInt("id"), rs.getString("question"), rs.getString("tip"), Boolean.valueOf(rs.getString("tipOnActive"))));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return advices;
    }

    public int getMaxID(String table) {

        int max = -1;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT MAX(id) FROM " + table);
            ResultSet rs = ps.executeQuery();
            rs.next();
            max = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return max;
    }

    public ArrayList getMultiAdvicesForTopic(int topic_id) {

        ArrayList<MultiAdvice> multiadvices = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM multiadvices WHERE topic_id=" + topic_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String[] ids = rs.getString("memberids").split("/");
                int[] advices_ids = new int[ids.length];

                for (int i = 0; i < ids.length; i++) {

                    advices_ids[i] = Integer.valueOf(ids[i]);

                }
                multiadvices.add(new MultiAdvice(advices_ids, rs.getInt("id"), rs.getString("tip")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return multiadvices;
    }

    public ArrayList getAdvicesFromValueForTopic(int topic_id) {

        ArrayList<AdviceFromValue> valueAdvices = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM tipvals WHERE topic_id=" + topic_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] tips = rs.getString("tiplist").split("/");
                String[] stringifiedValues = rs.getString("vals").split("/");
                float[] values = new float[stringifiedValues.length];

                for (int i = 0; i < stringifiedValues.length; i++) {

                    values[i] = Float.valueOf(stringifiedValues[i]);

                }
                valueAdvices.add(new AdviceFromValue(rs.getInt("id"), rs.getString("default_tip"), values, tips, rs.getString("question")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valueAdvices;
    }

    public void insertRegistry(Registry reg) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT MAX(registry_num) FROM registry WHERE topic_id=" + reg.getTopic_id());
            ResultSet max = ps.executeQuery();
            max.next();
            reg.setRegistry_num(max.getInt(1) + 1);

            ps = c.prepareStatement("INSERT INTO registry VALUES (0, " + reg.getTopic_id() + ", " + reg.getRegistry_num() + ", '" + reg.getSuccess() + "', '" + reg.getDescription() + "', '" + reg.getTips() + "', '" + reg.getTitle() + "')");
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Registry getRegistry(int id) {

        Registry reg = null;

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM registry WHERE id=" + id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            reg = new Registry(rs.getInt("topic_id"), rs.getInt("registry_num"), rs.getString("title"), rs.getString("success"), rs.getString("description"), rs.getString("tips"));

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reg;
    }
}
