package ru.job4j.sqlruparser;

import java.util.Calendar;

public class Vacancy {
    private String description;
    private String hrefVacancy;
    private String topicStarter;
    private String hrefTopicStarter;
    private Calendar lastComment;
    private boolean closed;

    public Vacancy(String desc, String hrefVac, String topStart, String hrefTS, Calendar comment, boolean closed) {
        this.description = desc;
        this.hrefVacancy = hrefVac;
        this.topicStarter = topStart;
        this.hrefTopicStarter = hrefTS;
        this.lastComment = comment;
        this.closed = closed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHrefVacancy() {
        return hrefVacancy;
    }

    public void setHrefVacancy(String hrefVacancy) {
        this.hrefVacancy = hrefVacancy;
    }

    public String getTopicStarter() {
        return topicStarter;
    }

    public void setTopicStarter(String topicStarter) {
        this.topicStarter = topicStarter;
    }

    public String getHrefTopicStarter() {
        return hrefTopicStarter;
    }

    public void setHrefTopicStarter(String hrefTopicStarter) {
        this.hrefTopicStarter = hrefTopicStarter;
    }

    public Calendar getLastComment() {
        return lastComment;
    }

    public void setLastComment(Calendar lastComment) {
        this.lastComment = lastComment;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;

        return hrefVacancy != null ? hrefVacancy.equals(vacancy.hrefVacancy) : vacancy.hrefVacancy == null;
    }

    @Override
    public int hashCode() {
        return hrefVacancy != null ? hrefVacancy.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Vacancy" + System.lineSeparator() +
                "description='" + description + '\'' + System.lineSeparator() +
                "hrefVacancy='" + hrefVacancy + '\'' + System.lineSeparator() +
                "topicStarter='" + topicStarter + '\'' + System.lineSeparator() +
                "hrefTopicStarter='" + hrefTopicStarter + '\'' + System.lineSeparator() +
                "lastComment='" + lastComment.getTime() + '\'' + System.lineSeparator() +
                "closed=" + (closed ? "Yes" : "No");
    }
}
