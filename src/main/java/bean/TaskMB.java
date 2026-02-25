package bean;



import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import dao.ProfileDAO; 
import dao.TaskDAO;
import model.Profile;
import model.Task;

@Named("taskMB")
@ViewScoped
public class TaskMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Task task;
    private Profile profile;
    
    
    private List<Task> taskList;
    private List<Profile> profiles;

    private TaskDAO taskDAO;
    private ProfileDAO profileDAO;

    @PostConstruct
    public void init() {
        taskDAO = new TaskDAO();
        profileDAO = new ProfileDAO();
        
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        Task tarefaQueVeioDoFlash = (Task) flash.get("tarefaEditada");
        
        
        if (tarefaQueVeioDoFlash != null) {
            this.task = tarefaQueVeioDoFlash;
            
            if (this.task.getResponsible() != null) {
                this.profile = this.task.getResponsible();
            } else {
                this.profile = new Profile();
            }
        } else {
            this.task = new Task();
            this.profile = new Profile();
        }
        
        updateTaskList();
        this.profiles = profileDAO.list(); 
    }


    public String saveTask() {
        
    	if (profile != null && profile.getId() != null) {
            task.setResponsible(profileDAO.findById(profile.getId()));
        } else {
            task.setResponsible(null);
        }
        
        task.setStatus(false);
        
        taskDAO.save(task);
        
        
        task = new Task();
        profile = new Profile();
        updateTaskList();
        
        return "listagem?faces-redirect=true"; 
    }

    public void deleteTask(Long id) {
        taskDAO.delete(id);
        updateTaskList();
    }

    public void doneTask(Long id) {
        Task t = taskDAO.findById(id);
        t.setStatus(true); 
        taskDAO.save(t);
        updateTaskList();
    }

   

    public void findTask() {
        
        this.taskList = taskDAO.filter(task.getId(), task.getTitle(), profile.getId(), task.getStatus());
    }

    public void updateTaskList() {
        
        this.taskList = taskDAO.listInProgress();
    }
    
    public String redirectToEdit(Long id) {
    	Task t = taskDAO.findById(id);
        
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("tarefaEditada", t);
        
        return "index?faces-redirect=true";
    }

   

    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }

    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) { this.profile = profile; }

    public List<Task> getTaskList() { return taskList; }
    public void setTaskList(List<Task> taskList) { this.taskList = taskList; }

    public List<Profile> getProfiles() { return profiles; }
    public void setProfiles(List<Profile> profiles) { this.profiles = profiles; }
}