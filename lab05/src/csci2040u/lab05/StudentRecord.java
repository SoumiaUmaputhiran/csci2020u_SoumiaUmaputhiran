package csci2040u.lab05;

public class StudentRecord{

    private String SID;
    private float assignments;
    private float midterm;
    private float finalExam;
    private float finalMark;
    private String letterGrade;

    public StudentRecord(String SID, float assignments, float midterm, float finalExam){
        this.SID = SID;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;
        if (finalExam >=0 && finalExam <=49){
            this.letterGrade = "F";
        }
        else if(finalExam >=50 && finalExam <=59){
            this.letterGrade = "D";
        }
        else if(finalExam >=60 && finalExam <=69){
            this.letterGrade = "C";
        }
        else if(finalExam >=70 && finalExam <=79){
            this.letterGrade = "B";
        }
        else if(finalExam >=80 && finalExam <=100){
            this.letterGrade = "A";
        }
    }

    public String getSID() {
        return this.SID;
    }
    public float getAssignments() {
        return this.assignments;
    }
    public float getMidterm() {
        return this.midterm;
    }
    public float getFinalExam() {
        return this.finalExam;
    }
    public float getFinalMark() {
        finalMark = (float)((assignments * 0.2) + (midterm * 0.3) + (finalExam * 0.5));
        return finalMark;
    }
    public String getLetterGrade() {
        return this.letterGrade;
    }
}