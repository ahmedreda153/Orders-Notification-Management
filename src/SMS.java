public class SMS implements Channel{

    @Override
    public void send(String subject, String content, String language) {
        System.out.println("SMS: " + subject + " " + content + " " + language);
    }
}