package kanban.model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kanban.view.Priority;

import java.time.LocalDate;

public class Task {
    private StringProperty title;
    private Priority priority;
    private LocalDate date;
    private StringProperty text;

    private ObjectProperty<ImageView> image;
    public ObjectProperty imageProperty() {
        return image;
    }

    public Task() {
        this.title =  new SimpleStringProperty("");
        this.priority = Priority.LOW;
        this.date = LocalDate.of(2019, 4, 16);
        this.text = new SimpleStringProperty("");

    }

    public Task(String title, Priority priority, LocalDate date, String text) {
        this.title = new SimpleStringProperty(title);
        this.priority = priority;
        this.date = date;
        this.text = new SimpleStringProperty(text);
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return title.toString();
    }

}
