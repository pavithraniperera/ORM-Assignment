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
        



        transaction.commit();
        session.close();
    }
}
