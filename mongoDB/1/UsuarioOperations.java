import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.Arrays;

public class UsuarioOperations {

    private final MongoCollection<Document> collection;

    public UsuarioOperations(MongoDatabase database) {
        this.collection = database.getCollection("usuarios");
    }

    public void inserirUsuarios() {
        Usuario u1 = new Usuario("Alice", 25);
        Usuario u2 = new Usuario("Bob", 30);
        Usuario u3 = new Usuario("Charlie", 35);

        collection.insertMany(Arrays.asList(
                u1.toDocument(),
                u2.toDocument(),
                u3.toDocument()
        ));

        System.out.println("Usuários inseridos.");
    }

    public void consultarUsuarios() {
        System.out.println("Consultando usuários:");
        for (Document doc : collection.find()) {
            Usuario usuario = Usuario.fromDocument(doc);
            System.out.println(usuario);
        }
    }

    public void atualizarBob() {
        collection.updateOne(
                Filters.eq("nome", "Bob"),
                Updates.set("idade", 32)
        );
        System.out.println("Bob atualizado para idade 32.");
    }

    public void removerCharlie() {
        collection.deleteOne(Filters.eq("nome", "Charlie"));
        System.out.println("Charlie removido.");
    }

    public static void main(String[] args) {
        MongoDBConnection connection = new MongoDBConnection();
        MongoDatabase db = connection.getDatabase();

        UsuarioOperations ops = new UsuarioOperations(db);

        ops.inserirUsuarios();
        ops.consultarUsuarios();

        ops.atualizarBob();
        ops.consultarUsuarios();

        ops.removerCharlie();
        ops.consultarUsuarios();

        connection.closeConnection();
    }
}