package ru.leoman.filesystemsp.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.leoman.filesystemsp.File;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    private static final List<File> ListOfFiles =  new ArrayList<>();

    @Override
    public void create(@NonNull File file) {

    }

    @Override
    public File read(UUID uuid) {
    return FileServiceImpl.ListOfFiles.stream().filter(file -> file.getId().equals(uuid)).findFirst().orElseThrow();
    }

    @Override
    public List<File> readAll() {
        return ListOfFiles;
    }

    @Override
    public void delete(UUID uuid) {
        File file = read(uuid);
        FileServiceImpl.ListOfFiles.remove(file);

    }
    public void add(File file){
        this.ListOfFiles.add(file);
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
