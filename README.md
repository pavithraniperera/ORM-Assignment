1)  String hql1 = "From Book WHERE publicationYear > :year";
   
2)   String hql2 ="Update Book SET price = price*1.1 WHERE author.id = :id ";
3)   
        Author author = session.get(Author.class, 2);
        if (author != null) {
            session.delete(author); // Delete the author
            System.out.println(" deleted successfully!");
        }

 4) String hql3 = "SELECT AVG(price) FROM Book";

5) String hql4 = "SELECT a, COUNT(b) FROM Author a LEFT JOIN a.books b GROUP BY a ";

6)  String hql5 = "SELECT b FROM Book b WHERE author.country = :country";

8)String hql6 = "SELECT a From Author a WHERE SIZE(a.books) > (SELECT AVG(SIZE(b.books)) FROM Author b)";

**EXPLANATIONs FOR ANNOTATIONS**

01) @JoinColumn Annotation - An author can write many books, and a book belongs to one author. This is a one-to-many relationship.The Join Column annotation helps map this relationship in this code.  It tells to program which column in one table (e.g., author_id in Books) refers to the primary key (usually id) in the other table (Authors). This creates the link between them.

02) CascadeType.REMOVE - When we delete an author, all their associated books are also deleted.

03) GenerateId annotation - When we create new entities, they need unique identifiers (IDs). The GenerateId annotation helps with this. It tells to program how to generate new IDs.
                            IDENTITY: The database assigns a unique ID when the entity is inserted.

