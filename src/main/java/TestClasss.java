package main.java;

public class TestClasss {
    public static void main(String[] args) {
        String name = "main";
        String schema = "logging";
        String query = "INSERT into logging.main(id, value) main main main logging VALUES (1, 'some text')";
        query = query.replace(name, "copy").replace(schema, "new_schema");
        System.out.println(query);
    }
}
