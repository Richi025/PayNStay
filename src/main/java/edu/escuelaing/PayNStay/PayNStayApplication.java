package edu.escuelaing.PayNStay;

import edu.escuelaing.PayNStay.Repository.Appointment.Appointment;
import edu.escuelaing.PayNStay.Repository.Appointment.Appointment.AppointmentStatus;
import edu.escuelaing.PayNStay.Repository.Appointment.AppointmentRepository;
import edu.escuelaing.PayNStay.Repository.Document.DocumentRepository;
import edu.escuelaing.PayNStay.Repository.Document.Documents;
import edu.escuelaing.PayNStay.Repository.Document.Documents.DocumentType;
import edu.escuelaing.PayNStay.Repository.Property.Property;
import edu.escuelaing.PayNStay.Repository.Property.Property.PropertyType;
import edu.escuelaing.PayNStay.Repository.Property.PropertyRepository;
import edu.escuelaing.PayNStay.Repository.Transaction.Transaction;
import edu.escuelaing.PayNStay.Repository.Transaction.Transaction.TransactionType;
import edu.escuelaing.PayNStay.Repository.Transaction.TransactionRepository;
import edu.escuelaing.PayNStay.Repository.User.UserDto;
import edu.escuelaing.PayNStay.Repository.User.UserDto.UserType;
import edu.escuelaing.PayNStay.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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
        // Insert a user
        UserDto user = new UserDto(UUID.randomUUID(), "John Doe", "john.doe@example.com", "password123", UserType.OWNER);
        userRepository.save(user);
        System.out.println("User inserted: " + user);

        // Insert a property
        Property property = new Property(UUID.randomUUID(), "123 Main St", "New York", 
                PropertyType.HOUSE, new BigDecimal("500000"), 120.5, 3, 2, 
                "A beautiful house in NYC", List.of("image1.jpg", "image2.jpg"), user.getId());
        propertyRepository.save(property);
        System.out.println("Property inserted: " + property);

        // Insert a transaction
        Transaction transaction = new Transaction(UUID.randomUUID(), property.getId(), user.getId(),
                TransactionType.PURCHASE, new BigDecimal("500000"));
        transactionRepository.save(transaction);
        System.out.println("Transaction inserted: " + transaction);

        // Insert an appointment
        Appointment appointment = new Appointment(UUID.randomUUID(), property.getId(), user.getId(), 
                LocalDate.now().atStartOfDay(), AppointmentStatus.CONFIRMED);
        appointmentRepository.save(appointment);
        System.out.println("Appointment inserted: " + appointment);

        // Insert a document
        Documents document = new Documents(UUID.randomUUID(), property.getId(), 
                DocumentType.CONTRACT, "https://example.com/document/contract.pdf");
        documentRepository.save(document);
        System.out.println("Document inserted: " + document);
    }
}
