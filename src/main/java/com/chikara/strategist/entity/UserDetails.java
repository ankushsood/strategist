package com.chikara.strategist.entity;

import java.util.ArrayList;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.chikara.strategist.constants.BookType;
import com.chikara.strategist.constants.Gender;
import com.chikara.strategist.constants.SchoolType;
import com.chikara.strategist.constants.SubjectStream;



public class UserDetails {

    public static Subject getSubjectList(String title, String desc, int 
numberOfBooks, Standard standard, Faculty faculty){
        Subject subject = new Subject();
        subject.setSubjectTitle(title);
        subject.setSubjectDescription(desc);
        subject.setStandard(standard);
        subject.setFaculty(faculty);
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book = null;
        for(int i = 0; i < numberOfBooks; i++){
            book = new Book();
            book.setBooktitle(title + "- Part: " + i);
            book.setBookType(BookType.CourseBook);
            book.setSubject(subject);
            bookList.add(book);
            
        }
        subject.setBookList(bookList);
        return subject;
    }
    
    public static List <Student> getStudentList(Standard standard, List<Subject> subjectList, int numberOfStudents){
        
    	List <Student> students = new ArrayList<Student>();
    	Student student = null;
    	for (int i = 1; i <= numberOfStudents; i++) {
        	student = new Student();
            student.setFirstName("Student_" + i + "_" + i );
            student.setCity("New Delhi");
            student.setAddress("Green fields Colony");
    		student.setLastName("LastName_" + i);
    		student.setMobileNumber("9971156315");
    		student.setSubjectList(subjectList);
    		student.setPincode(120003l);
    		student.setStandard(standard);
            students.add(student);
		}
    	
        return students;
    }
    
    public static void main(String[] args) {
        School school = new School();
        
        Faculty facRam = new Faculty();
        facRam.setFirstName("Ram");facRam.setCreated(new 
Date());facRam.setGender(Gender.MALE);facRam.setUpdated(new 
Date());facRam.setSchool(school);
        
        Faculty facShyam = new Faculty();
        facShyam.setFirstName("Shyam");facShyam.setCreated(new 
Date());facShyam.setGender(Gender.MALE);facShyam.setUpdated(new 
Date());facShyam.setSchool(school);
        
        
        
        Faculty facMohan = new Faculty();
        facMohan.setFirstName("Mohan");facMohan.setCreated(new 
Date());facMohan.setGender(Gender.MALE);facMohan.setUpdated(new 
Date());facMohan.setSchool(school);
        
        Faculty facSohan = new Faculty();
        facSohan.setFirstName("Sohan");facSohan.setCreated(new 
Date());facSohan.setGender(Gender.MALE);facSohan.setUpdated(new 
Date());facSohan.setSchool(school);
        
        Faculty facSita = new Faculty();
        facSita.setFirstName("Sita");facSita.setCreated(new 
Date());facSita.setGender(Gender.FEMALE);facSita.setUpdated(new 
Date());facSita.setSchool(school);
                
        Faculty facGeeta = new Faculty();
        facGeeta.setFirstName("Geeta");facGeeta.setCreated(new 
Date());facGeeta.setGender(Gender.FEMALE);facGeeta.setUpdated(new 
Date());facGeeta.setSchool(school);
        ArrayList<Faculty> facultyList = new ArrayList<Faculty>();
        
facultyList.add(facGeeta);facultyList.add(facSita);facultyList.add(facSohan);facultyList.add(facMohan);facultyList.add(facShyam);facultyList.add(facRam);
        Standard standXIA = new Standard();
        standXIA.setClassCode("XI");
        standXIA.setClassSection("A");
        standXIA.setCreated(new Date());
        standXIA.setStream(SubjectStream.GENERAL);
        standXIA.setUpdated(new Date());
        
        Subject maths = getSubjectList("Maths", "Mathematics", 3, standXIA, 
facRam);
        Subject english = getSubjectList("English", "English Language", 2, 
standXIA, facSita);
        Subject accounts = getSubjectList("Accounts", "Accounting and calculation", 2, standXIA, facMohan);
        Subject economics = getSubjectList("Economics", "Economics Growth", 
1, standXIA, facGeeta);
        Subject bs = getSubjectList("Business Studies", "Business Studies", 
2, standXIA, facShyam);
        Subject physicalEdu = getSubjectList("Physical Education", 
"Physical Education, Games, Cricket", 3, standXIA, facSohan);
        
        ArrayList<Subject> subjectList= new  ArrayList<Subject>();
        
subjectList.add(maths);subjectList.add(english);subjectList.add(accounts);subjectList.add(economics);subjectList.add(bs);subjectList.add(physicalEdu);
        standXIA.setSubjectList(subjectList);
        
        List<Student> stuList = getStudentList(standXIA, subjectList, 50);
        maths.setStudent(stuList);english.setStudent(stuList);accounts.setStudent(stuList);economics.setStudent(stuList);bs.setStudent(stuList);physicalEdu.setStudent(stuList);
        Standard standXIB = new Standard();
        standXIB.setClassCode("XI");
        standXIB.setClassSection("B");
        standXIB.setCreated(new Date());
        standXIB.setStream(SubjectStream.GENERAL);
        standXIB.setUpdated(new Date());
        standXIA.setFaculty(facultyList);
        standXIA.setStudentList(stuList);
        SessionFactory sessionFactory = new AnnotationConfiguration()
                .configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        school.setSchoolType(SchoolType.SENIOR_SECONDARY);
        school.setSchoolTitle("Tagore Public school");
        school.setSchoolType(SchoolType.METRIC);
        school.setFacultyList(facultyList);
        ArrayList<Standard> standList = new ArrayList<Standard>();
        standXIA.setSchool(school);
        standXIB.setSchool(school);
        standList.add(standXIA);
        standList.add(standXIB);
        school.setStandardList(standList);
        session.save(school);
        session.getTransaction().commit();
        
        session.close();
        
        /*System.out.println("---------Opening new session");
        session = sessionFactory.openSession();
        session.beginTransaction();
        School stn = (School) session.get(School.class, school.getId());
        System.out.println(stn.getSchoolTitle());
        school.setCreated(new Date());
        school.setUpdated(new Date());

        System.out.println("~~~~~~~~~~~~~~~~ GEtting second tym");
        
        School stn2 = (School) session.get(School.class, school.getId());
        System.out.println("~~~~~~~~~~~~~~~~"+stn2);
        
            session.getTransaction().commit();
        
        session.close();
        
        System.out.println(stn2.getStandardList().get(0).getClassCode());
*/        
    }

}