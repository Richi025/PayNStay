package edu.escuelaing.PayNStay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import edu.escuelaing.PayNStay.model.Appointment;
import edu.escuelaing.PayNStay.model.Documents;
import edu.escuelaing.PayNStay.model.Property;
import edu.escuelaing.PayNStay.model.Transaction;
import edu.escuelaing.PayNStay.model.User;
import edu.escuelaing.PayNStay.model.Appointment.AppointmentStatus;
import edu.escuelaing.PayNStay.model.Documents.DocumentType;
import edu.escuelaing.PayNStay.model.Property.PropertyType;
import edu.escuelaing.PayNStay.model.Transaction.TransactionType;
import edu.escuelaing.PayNStay.model.User.UserType;
import edu.escuelaing.PayNStay.repository.AppointmentRepository;
import edu.escuelaing.PayNStay.repository.DocumentRepository;
import edu.escuelaing.PayNStay.repository.PropertyRepository;
import edu.escuelaing.PayNStay.repository.TransactionRepository;
import edu.escuelaing.PayNStay.repository.UserRepository;
import edu.escuelaing.PayNStay.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableMongoRepositories
public class PayNStayApplication implements CommandLineRunner {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DocumentRepository documentRepository;

    public static void main(String[] args) {
        SpringApplication.run(PayNStayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("------------------------Insert multiple users----------------------------------");
        User user1 = new User(UUID.randomUUID(), "John Doe", "john.doe@example.com", "password123", UserType.OWNER);
        User user2 = new User(UUID.randomUUID(), "Jane Smith", "jane.smith@example.com", "password123", UserType.TENANT);
        userService.createUser(user1);
        userService.createUser(user2);
        System.out.println("Users inserted: " + user1 + ", " + user2);

        System.out.println("------------------------Insert multiple properties----------------------------------");
        Property property1 = new Property(UUID.randomUUID(), "123 Main St", "New York", 
                PropertyType.HOUSE, new BigDecimal("500000"), 120.5, 3, 2, 
                "A beautiful house in NYC", List.of("image1.jpg", "image2.jpg"), user1.getId());
        Property property2 = new Property(UUID.randomUUID(), "456 Elm St", "Los Angeles", 
                PropertyType.APARTMENT, new BigDecimal("300000"), 80.0, 2, 1, 
                "Cozy apartment in LA", List.of("image3.jpg", "image4.jpg"), user2.getId());
        propertyRepository.save(property1);
        propertyRepository.save(property2);
        System.out.println("Properties inserted: " + property1 + ", " + property2);

        System.out.println("------------------------Insert multiple transactions----------------------------------");
        Transaction transaction1 = new Transaction(UUID.randomUUID(), property1.getId(), user1.getId(),
                TransactionType.PURCHASE, new BigDecimal("500000"));
        Transaction transaction2 = new Transaction(UUID.randomUUID(), property2.getId(), user2.getId(),
                TransactionType.RENT, new BigDecimal("1500"));
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        System.out.println("Transactions inserted: " + transaction1 + ", " + transaction2);


        System.out.println("------------------------Insert multiple appointments----------------------------------");
        Appointment appointment1 = new Appointment(UUID.randomUUID(), property1.getId(), user1.getId(), 
                LocalDate.now().atStartOfDay(), AppointmentStatus.CONFIRMED);
        Appointment appointment2 = new Appointment(UUID.randomUUID(), property2.getId(), user2.getId(), 
                LocalDate.now().plusDays(1).atStartOfDay(), AppointmentStatus.PENDING);
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        System.out.println("Appointments inserted: " + appointment1 + ", " + appointment2);

        System.out.println("------------------------Insert multiple documents----------------------------------");
        Documents document1 = new Documents(UUID.randomUUID(), property1.getId(), 
                DocumentType.CONTRACT, "https://example.com/document/contract1.pdf");
        Documents document2 = new Documents(UUID.randomUUID(), property2.getId(), 
                DocumentType.PERMITS, "https://example.com/document/lease1.pdf");
        documentRepository.save(document1);
        documentRepository.save(document2);
        System.out.println("Documents inserted: " + document1 + ", " + document2);

        System.out.println("------------------------Update a user(Jhon Doe)----------------------------------");
        user1.setUsername("John Doe Update");
        userRepository.save(user1);
        System.out.println("User updated: " + user1);

        System.out.println("------------------------Retrieve and print all properties----------------------------------");
        List<Property> properties = propertyRepository.findAll();
        System.out.println("All properties in the database: " + properties);

        System.out.println("------------------------Delete one transaction----------------------------------");
        transactionRepository.deleteById(transaction1.getId());
        System.out.println("Transaction deleted: " + transaction1.getId());

        System.out.println("------------------------Retrieve and print all transactions----------------------------------");
        List<Transaction> remainingTransactions = transactionRepository.findAll();
        System.out.println("Remaining transactions: " + remainingTransactions);

        System.out.println("------------------------Retrieve all appointments and delete one----------------------------------");
        List<Appointment> appointments = appointmentRepository.findAll();
        System.out.println("All appointments: " + appointments);
        appointmentRepository.deleteById(appointment1.getId());
        System.out.println("Appointment deleted: " + appointment1.getId());

        System.out.println("------------------------Retrieve all remaining appointments----------------------------------");
        List<Appointment> remainingAppointments = appointmentRepository.findAll();
        System.out.println("Remaining appointments: " + remainingAppointments);

        System.out.println("------------------------Delete one property----------------------------------");
        propertyRepository.deleteById(property1.getId());
        System.out.println("Property deleted: " + property1.getId());

        System.out.println("------------------------Retrieve all remaining properties----------------------------------");
        List<Property> remainingProperties = propertyRepository.findAll();
        System.out.println("Remaining properties: " + remainingProperties);
    }
}
