package ru.leoman.filesystemsp.service;

import lombok.NonNull;
import ru.leoman.filesystemsp.File;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface FileService {
    void create(@NonNull File file) throws IOException;
    File read(UUID uuid) throws IOException;
    List<File> readAll() throws IOException;
    void delete(UUID uuid) throws IOException;
}
