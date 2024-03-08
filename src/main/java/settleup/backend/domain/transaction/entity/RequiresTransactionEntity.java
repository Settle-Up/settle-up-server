package settleup.backend.domain.transaction.entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import settleup.backend.domain.user.entity.UserEntity;
@Entity
@Table(name = "requires_transaction")
@Getter
@Setter
public class RequiresTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_uuid", nullable = false, unique = true)
    private String transactionUUID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id", nullable = false)
    private Receipt receipt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_user", nullable = false)
    private User senderUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_user", nullable = false)
    private User recipientUser;

    @Column(name = "transaction_amount", nullable = false)
    private Double transactionAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_sender_status", nullable = false)
    private Status isSenderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_recipient_status", nullable = false)
    private Status isRecipientStatus;
}

public enum Status {
    CLEAR, REJECT, PENDING;
}