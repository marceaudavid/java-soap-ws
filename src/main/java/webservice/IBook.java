package webservice;

import DTO.Book;
import DTO.Books;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IBook {
    @WebMethod
    Books getBooks();

    @WebMethod
    Book getBookById(String ISBN);

    @WebMethod
    String createBook(String ISBN, String title, Integer authorId);

    @WebMethod
    String updateBook(String ISBN, String title, Integer authorId);

    @WebMethod
    String deleteBook(String ISBN);
}