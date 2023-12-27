import java.util.ArrayList;

public class Notification {
    private String subject;

    private String template;
    //" Dear {name} , your booking of the {item} is confirmed. thanks for using our store :) "

    private String actualContent;
    //" Dear John , your booking of the iPhone 12 is confirmed. thanks for using our store :) "
    private String language;
    private Channel channel;

    private ArrayList<String> placeholders;
    //{name}, {item}

    private ArrayList<String> actualValues;
    //{"John", "iPhone 12"}

    public Notification(String subject, String template, String language, Channel channel, ArrayList<String> placeholders, ArrayList<String> actualValues) {
        this.subject = subject;
        this.template = template;
        this.language = language;
        this.channel = channel;
        this.placeholders = placeholders;
        this.actualValues = actualValues;
        actualContent = "";
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    private String setAcualContent(){
        // replace the placeholders with actual values in the actualContent
        actualContent = template;
        for(int i = 0; i < placeholders.size(); i++){
            String placeholder = placeholders.get(i);
            String actualValue = actualValues.get(i);
            actualContent = actualContent.replace(placeholder, actualValue);
        }
        return actualContent;
    }



    public void send(){
        actualContent= setAcualContent();
        channel.send(subject,actualContent,language);
    }
 
}