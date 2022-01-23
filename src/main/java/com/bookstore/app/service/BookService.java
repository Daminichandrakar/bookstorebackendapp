package com.bookstore.app.service;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.app.entity.BookEntity;
import com.bookstore.app.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	String line = "";
	
	public BookEntity getById(int id){
		if(bookRepository.getById(id)!=null) {
			return bookRepository.getById(id);
		}else {
			return null;
			
		}
	}
	
	public List<BookEntity> getAllList(){
		return bookRepository.findAll();
	}
	
	public BookEntity addBook(BookEntity bookEntity) {
		bookRepository.save(bookEntity);
		return bookEntity;
	}
	
	public List<BookEntity> bookSearch(String name){
		if(bookRepository.findAllByNameStartsWith(name)!=null) {
			return bookRepository.findAllByNameStartsWith(name);
		}else {
			return null;
			
		}
	}

	public String  loadCSV(){
		try {
			BufferedReader bufferedReader = new BufferedReader(new
					FileReader("src/main/resources/books.csv"));
			while ((line=bufferedReader.readLine()) != null) {
				String[] data = line.split(",");
				BookEntity bookEntity = new BookEntity();
				bookEntity.setAuthor(data[1].replaceAll("'", ""));
				bookEntity.setName(data[2].replaceAll("'", ""));
				bookEntity.setQuantity(data[3]);
				bookEntity.setImg(data[4]);
				bookEntity.setPrice(data[5]);
				IntStream.range(9, data.length - 1).forEach(column -> data[8] += "," + data[column]);
				bookEntity.setDescription(data[6]);
				bookEntity.setMarkedPrice(data[7]);
				bookEntity.setRating(data[8]);
				bookRepository.save(bookEntity);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Added";
	}


}
