package com.game.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(schema = "rpg",name = "player")
public class Player {
    @Id
    @Column( nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",unique = true, nullable = false, length = 12)
    private String name;
    @Column(name="title",length = 30,nullable = false)
    private String title;
    @Column(name="race",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Race race;
    @Column(name="profession",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Profession profession;
    @Column(name="birthday",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Column(name = "banned", nullable = false)
    private Boolean banned;
    @Column(name = "level", nullable = false)
    private Integer level;

    public Player() {
    }

    public Player(Long id, String name, String title, Race race, Profession profession, Date birthday, Boolean banned, Integer level) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}