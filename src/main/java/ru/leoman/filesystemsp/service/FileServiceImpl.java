package ru.leoman.filesystemsp.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.leoman.filesystemsp.File;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    private static final List<File> ListOfFiles =  new ArrayList<>();

    private static final String FILE_NAME = "data.txt";
    private static final int FIELD_WIDTH = 50; // Ширина каждого поля

    private static List<String> table;

    static {
        try {
            table = loadTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList <String[]> table1 = new ArrayList<>();
//    private static String[] headers = {"id", "Name", "Created", "Edited", "Type", "Size"};


    @Override
    public void create(@NonNull File file) throws IOException {
        table = loadTable();
        String row =  formatRow(file.getId().toString(), file.getName(), file.getTime_c().toString(), file.getTime_e().toString(), file.getFile_type(), Integer.toString(file.getBuffer_size()));
        table.add(row);
        saveTable(table);
    }

    @Override
    public File read(UUID uuid) throws IOException {
//        System.out.println(table.stream().filter(file -> file[0].equals(uuid.toString())).findFirst().orElseThrow());
        table = loadTable();
        String obj = new String();
        for (String s : table) {
            if (s.startsWith(uuid.toString())) {
                obj = s;
            }
        }

        String[] dataArray = obj.split("\\s+");
        for (String data : dataArray) {
            System.out.println(dataArray[2].trim());
        }
        File file = new File();
        file.setId(UUID.fromString(dataArray[0].trim()));
        file.setName(dataArray[1].trim());
        file.setTime_c(LocalDateTime.parse(dataArray[2].trim()));
        file.setTime_e(LocalDateTime.parse(dataArray[3].trim()));
        file.setFile_type(dataArray[4].trim());
        file.setBuffer_size(Integer.parseInt(dataArray[5].trim()));
        return  file;
    }

    @Override
    public List<File> readAll() throws IOException {
        table = loadTable();
        ListOfFiles.clear();
        for (String rowData : table) {
            System.out.println(rowData);
            String[] dataArray = rowData.split("\\s+");
            File file = new File();
            file.setId(UUID.fromString(dataArray[0].trim()));
            file.setName(dataArray[1].trim());
            file.setTime_c(LocalDateTime.parse(dataArray[2].trim()));
            file.setTime_e(LocalDateTime.parse(dataArray[3].trim()));
            file.setFile_type(dataArray[4].trim());
            file.setBuffer_size(Integer.parseInt(dataArray[5].trim()));
            ListOfFiles.add(file);
        }
        return ListOfFiles;
    }

    @Override
    public void delete(UUID uuid) throws IOException {
        table = loadTable();
        deleteRow(table,uuid.toString());
        saveTable(table);
    }
    public void add(File file){
        this.ListOfFiles.add(file);
        }
    public static List<String> loadTable() throws IOException {
        List<String> table = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                table.add(line);
            }
        } catch (FileNotFoundException ignored) {
            // Если файл не найден, создаем новую таблицу
        }
        return table;
    }
    public static void saveTable(List<String> table) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String rowData : table) {
                writer.write(rowData);
                writer.newLine();
            }
        }
    }

    public  static String formatRow(String field1, String field2, String field3, String field4, String field5, String field6) {
        return String.format("%-40s%-40s%-40s%-40s%-40s%-40s", field1, field2, field3, field4, field5, field6);
    }
    public static void updateRow(List<String> table, String id, String field2, String field3, String field4, String field5, String field6) {
        String updatedRow = formatRow(id, field2, field3, field4, field5, field6);
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).startsWith(id)) {
                table.set(i, updatedRow);
                break;
            }
        }
    }

    // Метод для удаления данных из таблицы
    public static void deleteRow(List<String> table, String id) {
        table.removeIf(rowData -> rowData.startsWith(id));
    }

    // Метод для вывода таблицы в консоль
    public static void printTable(List<String> table) {
        for (String rowData : table) {
            System.out.println(rowData);
        }
    }

}

//private static final List<File> ListOfFiles =  new ArrayList<>();
//FileList(File file) {
//    this.ListOfFiles.add(file);
//};
//public void displayOf(){
//    System.out.println(ListOfFiles);
//}
//public File  read(UUID uuid) {
//    return FileList.ListOfFiles.stream().filter(file -> file.getId().equals(uuid)).findFirst().orElseThrow();
//}
//
//public void add(File file){
//    this.ListOfFiles.add(file);
//}
//
//public List<File> readAll(){
//    return ListOfFiles;
//}
//
//public void delete(UUID uuid){
//    File file = read(uuid);
//    FileList.ListOfFiles.remove(file);
//}
