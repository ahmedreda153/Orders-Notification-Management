public interface Channel {
    void send(String subject, String content, String language);
}