package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "series")
    private int series;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_link")
    private User user_link;

    public Car() {};

    public Car(String name, int series) {
        this.name = name;
        this.series = series;
    }

    public Car(String name, int series, User user_link) {
        this.name = name;
        this.series = series;
        this.user_link = user_link;
    }

    public String getName() {
        return name;
    }

    public int getSeries() {
        return series;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeries(int series) {
        this.series = series;
    }


}
