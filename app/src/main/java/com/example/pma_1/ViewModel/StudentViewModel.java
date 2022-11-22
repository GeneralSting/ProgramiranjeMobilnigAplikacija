package com.example.pma_1.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pma_1.Classes.Student.Student;
import com.example.pma_1.Classes.Student.Subject;
import com.example.pma_1.Classes.Student.Summary;
import com.example.pma_1.Classes.StudentsRV.StudentRecyclerList;

import java.util.List;

public class StudentViewModel extends ViewModel {

    private final MutableLiveData<Student> studentLiveData = new MutableLiveData<Student>();     //change data will trigger function
    public void setStudent(Student student) {
        studentLiveData.setValue(student);
    }
    public LiveData<Student> getLiveDataStudent() {
        return studentLiveData;
    }

    public final MutableLiveData<Boolean> checkStudent = new MutableLiveData<>();           //value = true
    public void setCheckStudent(Boolean check) {
        checkStudent.setValue(check);
    }
    public LiveData<Boolean> getLiveCheckStudent() {
        return checkStudent;
    }


    private final MutableLiveData<Subject> subjectLiveData = new MutableLiveData<Subject>();
    public void setSubject(Subject subject) {
        subjectLiveData.setValue(subject);
    }
    public LiveData<Subject> getLiveDataSubject() {
        return subjectLiveData;
    }

    public final MutableLiveData<Boolean> checkSubject = new MutableLiveData<>();           //value = true
    public void setCheckSubject(Boolean check) {
        checkSubject.setValue(check);
    }
    public LiveData<Boolean> getLiveCheckSubject() {
        return checkSubject;
    }


    private final MutableLiveData<Summary> summaryLiveData = new MutableLiveData<Summary>();
    public void setSummary(Summary summary) {
        summaryLiveData.setValue(summary);
    }
    public LiveData<Summary> getLiveDataSummary() {
        return summaryLiveData;
    }

    public final MutableLiveData<Boolean> checkSummary = new MutableLiveData<>();           //value = true
    public void setCheckSummary(Boolean check) {
        checkSummary.setValue(check);
    }
    public LiveData<Boolean> getLiveCheckSummary() {
        return checkSummary;
    }


    private final MutableLiveData<List<StudentRecyclerList>> studentRecyclerListLiveData = new MutableLiveData<List<StudentRecyclerList>>();
    public void setStudentRecyclerList(List<StudentRecyclerList> studentRecyclerList) {
        studentRecyclerListLiveData.setValue(studentRecyclerList);
    }
    public LiveData<List<StudentRecyclerList>> getLiveDataStudentRecyclerList() {
        return studentRecyclerListLiveData;
    }

    public final MutableLiveData<Boolean> checkStudentRecyclerList = new MutableLiveData<>();           //value = true
    public void setcheckStudentRecyclerList(Boolean check) {
        checkStudentRecyclerList.setValue(check);
    }
    public LiveData<Boolean> getcheckStudentRecyclerList() {
        return checkStudentRecyclerList;
    }
}
