package org.sourceit.db;

import org.sourceit.entities.Applicant;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ApplicantDBProviderTest  {

    private ApplicantDBProvider provider = ApplicantDBProvider.INSTANCE;

    @BeforeTest
    public void beforeDelete() {
        try {
            for (Applicant applicant : provider.getApplicants()) {
                provider.deleteApplicant(applicant.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicants() {
        try {
            List applicants = provider.getApplicants();
            Assert.assertEquals(applicants.size() == 0, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveApplicant() {
        try {
            provider.saveApplicant(new Applicant(3, "Akop", "Vardanian", 2016));

            Applicant applicant = null;
            Long tempId = null;

            for (Applicant temp : provider.getApplicants()) {
                if (temp.getFirstName().equalsIgnoreCase("Akop")) {
                    tempId = temp.getId();
                    temp = provider.getApplicant(temp.getId());
                }
            }
            for (Applicant temp : provider.getApplicants()) {
                if (temp.getLastName().equalsIgnoreCase("Vardanian")) {
                    tempId = temp.getId();
                    temp = provider.getApplicant(temp.getId());
                }
            }
            for (Applicant temp : provider.getApplicants()) {
                if (temp.getEntranceYear()==2016) {
                    tempId = temp.getId();
                    temp = provider.getApplicant(temp.getId());
                }
            }
            for (Applicant temp : provider.getApplicants()) {
                if (temp.getProfessionId().equals("Android")) {
                    tempId = temp.getId();
                    temp = provider.getApplicant(temp.getId());
                }
            }

            Assert.assertEquals(applicant.getId() == tempId, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteApplicant() {
        try {
            provider.deleteApplicant(1L);

            List applicants = provider.getApplicants();

            Assert.assertEquals(applicants.size() == 0, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicnatsWithResult() {
        try {
            // 2
            provider.saveApplicant(new Applicant(3, "Akop", "Vardanian", 2014));
            // 3
            provider.saveApplicant(new Applicant(3, "Akop", "Vardanian", 2015));
            // 4
            provider.saveApplicant(new Applicant(3, "Akop", "Vardanian", 2016));
            List professions = provider.getApplicants();

            Assert.assertEquals(professions.size() == 3, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateApplicant() {
        try {
            // 2
            provider.saveApplicant(new Applicant(3, "Akop", "Vardanian", 2014));
            // 3
            Applicant applicant = (new Applicant(3, "Akop", "Vardanian", 2015));
            applicant.setId(3L);
            provider.saveApplicant(applicant);

            Assert.assertEquals(provider.getApplicant(3L).getFirstName(), "Akop");
            Assert.assertEquals(provider.getApplicant(3L).getLastName(), "Vardanian");
            Assert.assertEquals(provider.getApplicant(3L).getEntranceYear(), 2014);
            Assert.assertEquals(provider.getApplicant(3L).getProfessionId(), "Android");
            // 4
            provider.saveApplicant(new Applicant(3, "Akop", "Vardanian", 2014));
            List professions = provider.getApplicants();

            Assert.assertEquals(professions.size() == 3, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
