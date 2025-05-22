package com.casdeveloper.model.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtilities {

    public String loadTextFile(File f) {
        try {
            BufferedReader r = new BufferedReader(new FileReader(f));
            StringWriter w = new StringWriter();

            try {
                String line = r.readLine();
                while (null != line) {
                    w.append(line).append("\n");
                    line = r.readLine();
                }

                return w.toString();
            } finally {
                r.close();
                w.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            return "";
        }
    }

    public List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return filenames;
    }

}
