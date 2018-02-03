package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "node")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Node implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long parentId;

    @NotBlank
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String detail;

    private final ArrayList<Node> children = new ArrayList<>();

    private void print(int depth, PrintWriter pw) {
        for (int i = 0; i < depth; i++) {
            pw.print("\t");
        }

        pw.println("[" + id + ", " + parentId + ", " + code + ", " + description + ", " + detail + "]");
        for (Node child : children) {
            child.print(depth + 1, pw);
        }
    }

    @Override
    public String toString() {
        StringWriter writer = new StringWriter();
        print(0, new PrintWriter(writer));
        return writer.toString();
    }

    public long getParentId() {
        return parentId;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDetail() {
        return detail;
    }

    public long getId() {
        return id;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return id == node.id &&
                parentId == node.parentId &&
                Objects.equals(children, node.children) &&
                Objects.equals(code, node.code) &&
                Objects.equals(description, node.description) &&
                Objects.equals(detail, node.detail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(children, id, parentId, code, description, detail);
    }
}
