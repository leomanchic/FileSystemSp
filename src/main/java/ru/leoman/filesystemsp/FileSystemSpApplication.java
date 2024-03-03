package ru.leoman.filesystemsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.leoman.filesystemsp.service.FileServiceImpl;

import java.io.IOException;
import java.util.UUID;

@SpringBootApplication
public class FileSystemSpApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(FileSystemSpApplication.class, args);
		File file = new File("Naril", "txt", 13);
//		file.TableView();
		FileServiceImpl arf = new FileServiceImpl();
		arf.create(file);
//		System.out.println(file.getId().toString());
//		System.out.println(arf.readAll());
		arf.delete(UUID.fromString("27382827-c122-48e3-a795-a867c3e15d30"));
//		File f = arf.read(file.getId());
//		f.TableView();
//		File t = arf.read(file.getId());

	}

}
