package patterns.adapter;

interface Writer{
    public void save(String data);
}

class NewWriter{
    public void save(String data, int color){

        System.out.println("Save data: " + data + " color: " + color);
    }
}

class DataWriter implements Writer{

    @Override
    public void save(String data){

        NewWriter adapter = new NewWriter();
        adapter.save(data, 0);
    }
}

public class DesignPatternsAdapter{

    public static void main(String[] args){

        Writer writer = new DataWriter();
        writer.save("Super value");
    }
}