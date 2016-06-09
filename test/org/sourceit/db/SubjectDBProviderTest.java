package org.sourceit.db;

import org.sourceit.entities.Subject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class SubjectDBProviderTest {

    private SubjectDBProvider provider = SubjectDBProvider.INSTANCE;

    @BeforeTest
    public void beforeDelete() {
        try {
            for (Subject subject : provider.getSubjects()) {
                provider.deleteSubject(subject.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSubjects() {
        try {
            List subjects = provider.getSubjects();
            Assert.assertEquals(subjects.size() == 0, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveSubject() {
        try {
            provider.saveSubject(new Subject("Java"));

            Subject subject = null;
            Long tempId = null;

            for (Subject temp : provider.getSubjects()) {
                if (temp.getSubjectName().equalsIgnoreCase("Java")) {
                    tempId = temp.getId();
                    temp = provider.getSubject(temp.getId());
                }
            }

            Assert.assertEquals(subject.getId() == tempId, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteSubject() {
        try {
            provider.deleteSubject(1L);

            List subjects = provider.getSubjects();

            Assert.assertEquals(subjects.size() == 0, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSubjectsWithResult() {
        try {
            // 2
            provider.saveSubject(new Subject("Java"));
            // 3
            provider.saveSubject(new Subject("Android"));
            // 4
            provider.saveSubject(new Subject("Mathematic"));
            List subjects = provider.getSubjects();

            Assert.assertEquals(subjects.size() == 3, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateSubject() {
        try {
            // 2
            provider.saveSubject(new Subject("Java"));
            // 3
            Subject subject = new Subject("Android");
            subject.setId(3L);
            provider.saveSubject(subject);

            Assert.assertEquals(provider.getSubject(3L).getSubjectName(), "Mathematic");
            // 4
            provider.saveSubject(new Subject("Java"));
            List subjects = provider.getSubjects();

            Assert.assertEquals(subjects.size() == 3, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

