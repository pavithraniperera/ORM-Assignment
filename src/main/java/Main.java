import config.FactoryConfiguration;
import entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.Year;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // Question -01
        String hql = "From Book WHERE publicationYear > :year";

        Query query = session.createQuery(hql);
        query.setParameter("year", Year.of(2010));


        List<Book> books = query.list();
        for (Book book : books){
            System.out.println(book.getTitle());
        }

         //Question-02
        String hql2 ="Update Book SET price = price*1.1 WHERE author.id = :id ";
        Query query1 = session.createQuery(hql2);
        query1.setParameter("id",1);
        int i = query1.executeUpdate();
        if (i>0){
            System.out.println("updated");
        }

           //Question-03

        Author author = session.get(Author.class, 2);
        if (author != null) {
            session.delete(author); // Delete the author
            System.out.println(" deleted successfully!");
        }

          //Question-04
        String hql3 = "SELECT AVG(price) FROM Book";
        Query query2 = session.createQuery(hql3);
        Double averagePrice = (Double) query2.uniqueResult();
        System.out.println("Average price of all Books " + averagePrice);

         //Question-05
      
        String hql4 = "SELECT a, COUNT(b) FROM Author a LEFT JOIN a.books b GROUP BY a ";
        Query query3 = session.createQuery(hql4);
        List<Object[]> results = query3.list();
        for (Object[] result : results) {
            Author author = (Author) result[0];
            Long bookCount = (Long) result[1];
            System.out.println("Author: " + author.getName() + ", Book Count: " + bookCount);
        }

         //Question -06

        String hql5 = "SELECT b FROM Book b WHERE author.country = :country";
        Query query4 = session.createQuery(hql5);
        query4.setParameter("country","SriLanka");
        List<Book> books = query4.list();
        for (Book book : books){
            System.out.println(book.getTitle());
        }

        
        //Question -08

        String hql6 = "SELECT a From Author a WHERE SIZE(a.books) > (SELECT AVG(SIZE(b.books)) FROM Author b)";
        Query query5 = session.createQuery(hql6);
        List<Author> authors = query5.list();
        for (Author author : authors){
            System.out.println(author.getName());
        }

        



        transaction.commit();
        session.close();
    }
}
