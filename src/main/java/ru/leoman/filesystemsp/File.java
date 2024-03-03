package ru.leoman.filesystemsp;

import com.github.freva.asciitable.AsciiTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
@NoArgsConstructor
@Getter
@Setter
public class File {
    private String name;
    private UUID id;
    private String file_type;
    private LocalDateTime time_c;
    private LocalDateTime time_e;
    private int buffer_size;
    void displayInfo(){
        System.out.println(name);
        System.out.println(id);
        System.out.println(file_type);
        System.out.println(time_c);
        System.out.println(time_e);
        System.out.println(buffer_size);
    }

    public void setName(String name){
        this.name = name;
        this.time_e = LocalDateTime.now();
    }

    public void setFile_type(String file_type){
        this.file_type = file_type;
        this.time_e = LocalDateTime.now();
    }

    public void setBuffer_size(int buffer_size){
        this.buffer_size = buffer_size;
        this.time_e = LocalDateTime.now();
    }

    //   Переопределяем функцию toString, для типа File
    @Override
    public String toString() {
        return "File {" + "\nuuid= " + this.id + ", \nname= " + this.name + "\nFileType= "
                + this.file_type + "\nSize= " + this.buffer_size + "\nCreated at= " +this.time_c + "\nEdited at= " + this.time_e + "\n}";
    }

    public void TableView(){
        ArrayList<String[]> table = new ArrayList<>();
        String[] headers = {"id", "Name", "Created", "Edited", "Type", "Size"};
        String[] row = {this.id.toString(),this.name, this.time_c.toString(), this.time_e.toString(), this.file_type, Integer.toString(this.buffer_size)};
        table.add(row);
        String[][] strings = table.toArray(String[][]::new);
//        String[][] data = {
//                {"1", "Mercury", "0.382", "0.06", "minimal"},
//                {"2", "Venus", "0.949", "0.82", "Carbon dioxide, Nitrogen"},
//                {"3", "Earth", "1.000", "1.00", "Nitrogen, Oxygen, Argon"},
//                {"4", "Mars", "0.532", "0.11", "Carbon dioxide, Nitrogen, Argon"}};

        System.out.println(AsciiTable.getTable(headers, strings));
    }
    File(String name, String file_type, int buffer_size){
        this.name = name;
        id = UUID.randomUUID();
        time_c = time_e = LocalDateTime.now();
        this.file_type =file_type;
        this.buffer_size = buffer_size;

    }
}