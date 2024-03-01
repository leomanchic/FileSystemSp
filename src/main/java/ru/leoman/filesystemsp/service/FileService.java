package ru.leoman.filesystemsp.service;

import lombok.NonNull;
import ru.leoman.filesystemsp.File;

import java.util.List;
import java.util.UUID;

public interface FileService {
    void create(@NonNull File file);
    File read(UUID uuid);
    List<File> readAll();
    void delete(UUID uuid);
}
