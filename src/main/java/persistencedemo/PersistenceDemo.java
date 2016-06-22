package persistencedemo;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;




public class PersistenceDemo {

    /**
     * @param args the command line arguments
     */
    private static void printResult(Object result) throws Exception {
        if (result == null) {
            System.out.print("NULL");
        } else if (result instanceof Object[]) {
            Object[] row = (Object[]) result;
            System.out.print("[");
            for (int i = 0; i < row.length; i++) {
                printResult(row[i]);
            }            System.out.print("]");
        } else if (result instanceof Long ||
                result instanceof Double ||
                result instanceof String) {
            System.out.print(result.getClass().getName() + ": " + result);
        } else {
            System.out.print(ReflectionToStringBuilder.toString(result,
                    ToStringStyle.SHORT_PREFIX_STYLE));
        }        System.out.println();
    }
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceDemoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        City c1 = new City(new java.math.BigDecimal(40), "Russia", "test", "Ekb", "Ural");
        City c2 = new City(new java.math.BigDecimal(41), "Russia2", "test2", "Ekb2", "Ural2");
        em.persist(c1);
        em.persist(c2);

        Attractions a1 = new Attractions(new java.math.BigDecimal(50),"Описание атракциона 1","Колесо обозрения" );
        Attractions a2 = new Attractions(new java.math.BigDecimal(51),"Описание атракциона 2","Колесо" );
        Attractions a3 = new Attractions(new java.math.BigDecimal(52),"Description 3","TopGun" );
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);

        Hotel h1 = new Hotel(new java.math.BigDecimal(60),"Lenina 5","Movzoley","000", c1);
        Hotel h2 = new Hotel(new java.math.BigDecimal(61),"Lenina 55","California","010", c2);
        Hotel h3 = new Hotel(new java.math.BigDecimal(62),"Stalina 155","Gulag","04564600", c1);
        Hotel h4 = new Hotel(new java.math.BigDecimal(63),"Gromova 2","Плаза","010");
        Hotel h5 = new Hotel(new java.math.BigDecimal(64),"Stalina 155","Хаят","04564600");

        em.persist(h1);
        em.persist(h2);
        em.persist(h3);
        em.persist(h4);
        em.persist(h5);

        List hotelCol = new ArrayList();
        hotelCol.add(h4);
        hotelCol.add(h5);

        City c3 = new City(new java.math.BigDecimal(43), "USA", "Subtest", "New York", "Columbia", hotelCol);
        em.persist(c3);


        em.getTransaction().commit();

        String q1 = "SELECT c from City c";
        List<City> CityList = em.createQuery(q1, City.class).getResultList();
        int count = 0;
        System.out.println("Query 1");
        for (City Gorod : CityList) {
            System.out.println(Gorod.toString());
        }

        String q2 = "SELECT h from Hotel h";
        List<Hotel> HotelList = em.createQuery(q2, Hotel.class).getResultList();
        count = 0;
        System.out.println();
        System.out.println("Query 2");
        for (Hotel H : HotelList) {
            City gor = H.getCity();
            if (gor != null)
            {
            System.out.println(H.getName()+" "+ H.getAddress()+ " gorod="+ gor.getName());
            }
            else
            {
                System.out.println(H.getName()+" "+ H.getAddress()+ " gorod=PUSTO");
            }
        }

        String q3 = "SELECT h.name, c.name from Hotel h, City c WHERE h.city.name=c.name";

        List<Object> HotelList2 = em.createQuery(q3).getResultList();
        count = 0;
        System.out.println();
        System.out.println("Query 3");
        for (Iterator i = HotelList2.iterator(); i.hasNext();) {
            Object[] values = (Object[]) i.next();

            System.out.println(++count + ": " +
                    values[0] + " in city " + values[1]);
        }


        count = 0;
        System.out.println("Write city id");

        String param = System.console().readLine();

        System.out.println("Query 4 for cityid="+ param);
        String q4 = "SELECT h.name, c.name, c.country from Hotel h JOIN h.city c WHERE c.id=:cityid ";
        List<Object> HotelList3 = em.createQuery(q4).setParameter("cityid", new java.math.BigDecimal(param)).getResultList();
        for (Iterator i = HotelList3.iterator(); i.hasNext();) {

            Object[] values = (Object[]) i.next();
            System.out.println(++count + ": " +
                    values[0] + " in city " + values[1]+ " country="+ values[2]);

        }

        em.close();
        emf.close();
        System.out.println("Press any key to exit");
        param = System.console().readLine();
    }





}
