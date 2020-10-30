package webservice;

import DTO.Author;
import DTO.Authors;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IAuthor {
    @WebMethod
    Authors getAuthors();

    @WebMethod
    Author getAuthorById(Integer id);

    @WebMethod
    String createAuthor(String firstname, String lastname);

    @WebMethod
    String updateAuthor(Integer id, String firstname, String lastname);

    @WebMethod
    String deleteAuthor(Integer id);
}