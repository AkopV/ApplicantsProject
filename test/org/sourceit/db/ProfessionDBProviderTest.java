package org.sourceit.db;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.sourceit.entities.Profession;

import java.util.List;

public class ProfessionDBProviderTest {

    private ProfessionDBProvider provider = ProfessionDBProvider.INSTANCE;

    @BeforeTest
     public void beforeDelete() {
        try {
            for (Profession profession : provider.getProfessions()) {
                provider.deleteProfession(profession.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getProfessions() {
        try {
            List professions = provider.getProfessions();
            Assert.assertEquals(professions.size() == 0, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveProfession() {
        try {
            provider.saveProfession(new Profession("Computer Science"));

            Profession profession = null;
            Long tempId = null;

            for (Profession temp : provider.getProfessions()) {
                if (temp.getProfessionName().equalsIgnoreCase("Computer Science")) {
                    tempId = temp.getId();
                    temp = provider.getProfession(temp.getId());
                }
            }

            Assert.assertEquals(profession.getId() == tempId, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteProfession() {
        try {
            provider.deleteProfession(1L);

            List professions = provider.getProfessions();

            Assert.assertEquals(professions.size() == 0, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getProfessionsWithResult() {
        try {
            // 2
            provider.saveProfession(new Profession("Computer Science"));
            // 3
            provider.saveProfession(new Profession("Nuclear Physics"));
            // 4
            provider.saveProfession(new Profession("System administration"));
            List professions = provider.getProfessions();

            Assert.assertEquals(professions.size() == 3, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateProfession() {
        try {
            // 2
            provider.saveProfession(new Profession("Computer Science"));
            // 3
            Profession profession = new Profession("Nuclear Reactors");
            profession.setId(3L);
            provider.saveProfession(profession);

            Assert.assertEquals(provider.getProfession(3L).getProfessionName(), "Nuclear Reactors");
            // 4
            provider.saveProfession(new Profession("System administration"));
            List professions = provider.getProfessions();

            Assert.assertEquals(professions.size() == 3, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
