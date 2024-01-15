import java.util.List;
import java.util.Scanner;

public class TeacherController {
    private TeacherService teacherService;
    private TeacherView teacherView;

    public TeacherController(TeacherService teacherService, TeacherView teacherView) {
        this.teacherService = teacherService;
        this.teacherView = teacherView;
    }

    public void createTeacher(String name, String subject) {
        Teacher newTeacher = new Teacher(teacherService.getAllTeachers().size() + 1, name, subject);
        teacherService.addTeacher(newTeacher);
    }

    public void updateTeacher(int id, String name, String subject) {
        Teacher updatedTeacher = new Teacher(id, name, subject);
        teacherService.updateTeacher(id, updatedTeacher);
    }

    public void showAllTeachers() {
        List<Teacher> allTeachers = teacherService.getAllTeachers();
        teacherView.printTeachers(allTeachers);
    }

    public static void main(String[] args) {
        TeacherService teacherService = new TeacherService();
        TeacherView teacherView = new TeacherView();
        TeacherController teacherController = new TeacherController(teacherService, teacherView);

        teacherController.createTeacher("Марк Цукерберг", "Алгебра и геометрия");
        teacherController.createTeacher("Илон Маск", "Психология");
        teacherController.createTeacher("Сэм Альтман", "Органическая химия");

        teacherController.showAllTeachers();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите идентификатор учителя, которого вы хотите обновить:");
        int teacherId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("Введите имя нового учителя:");
        String newName = scanner.nextLine();
        System.out.println("Введите новый предмет учителя:");
        String newSubject = scanner.nextLine();

        teacherController.updateTeacher(teacherId, newName, newSubject);

        teacherController.showAllTeachers();
    }
}