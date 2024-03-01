package ru.leoman.filesystemsp;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@NoArgsConstructor
@Getter
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

    //    Блок сеттеров и геттеров для Названия, Типа файлов, Размер
//    public String getName(){
//        return this.name;
//    }
//    public String getFile_type(){
//        return this.file_type;
//    }
//    public int getBuffer_size(){
//        return this.buffer_size;
//    }
//    public UUID getId(){return this.id;}

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

    File(String name, String file_type, int buffer_size){
        this.name = name;
        id = UUID.randomUUID();
        time_c = time_e = LocalDateTime.now();
        this.file_type =file_type;
        this.buffer_size = buffer_size;

    }
}