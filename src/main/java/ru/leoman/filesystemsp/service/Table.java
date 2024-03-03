package ru.leoman.filesystemsp.service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Table {

    private static final String FILE_NAME = "data.txt";
    private static final int FIELD_WIDTH = 70; // Ширина каждого поля

    public static void main(String[] args) {
        try {
            // Создание или загрузка таблицы при запуске приложения
            List<String[]> table = loadTable();

            // Пример добавления данных в таблицу
            String[] rowData1 = {"d7840b81-1e4a-4932-976f-ca4048ebae7b", "2024-03-03T13:20:19.716620600", "2024-03-03T13:20:19.716620600", "25" , "gfs"};
            String[] rowData2 = {"2", "Jane", "Smith", "30"};
            table.add(rowData1);
            table.add(rowData2);

            // Пример обновления данных в таблице
            updateRow(table, "2", "Jane", "Smith", "31");

            // Пример удаления данных из таблицы
            deleteRow(table, "d7840b81-1e4a-4932-976f-ca4048ebae7b");

            // Вывод таблицы
            printTable(table);

            // Сохранение таблицы в файл
            saveTable(table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для загрузки таблицы из файла
    private static List<String[]> loadTable() throws IOException {
        List<String[]> table = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = new String[4];
                // Чтение данных с учетом фиксированной ширины полей
                for (int i = 0; i < 4; i++) {
                    int endIndex = Math.min((i + 1) * FIELD_WIDTH, line.length());
                    rowData[i] = line.substring(i * FIELD_WIDTH, endIndex).trim();
                }
                table.add(rowData);
            }
        } catch (FileNotFoundException ignored) {
            // Если файл не найден, создаем новую таблицу
        }
        return table;
    }

    // Метод для сохранения таблицы в файл
    private static void saveTable(List<String[]> table) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            for (String[] rowData : table) {
                for (String field : rowData) {
                    // Запись данных с учетом фиксированной ширины полей
                    file.writeBytes(String.format("%-" + FIELD_WIDTH + "s", field));
                }
                file.writeBytes(System.lineSeparator());
            }
        }
    }

    // Метод для обновления данных в таблице
    private static void updateRow(List<String[]> table, String id, String firstName, String lastName, String age) {
        for (String[] rowData : table) {
            if (rowData[0].equals(id)) {
                rowData[1] = firstName;
                rowData[2] = lastName;
                rowData[3] = age;
                break; // Найден нужный элемент, выходим из цикла
            }
        }
    }

    // Метод для удаления данных из таблицы
    private static void deleteRow(List<String[]> table, String id) {
        table.removeIf(rowData -> rowData[0].equals(id));
    }

    // Метод для вывода таблицы в консоль
    private static void printTable(List<String[]> table) {
        for (String[] rowData : table) {
            for (String field : rowData) {
                System.out.print(field + "\t");
            }
            System.out.println();
        }
    }
}
