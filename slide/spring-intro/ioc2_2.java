public interface IDeviceWriter { 
    public void saveToDevice(); 
} 

public class BusinessObject { 
    private IDeviceWriter writer; 

    public void setWriter(IDeviceWriter writer) { ... }

    public void save() { 
        writer.saveToDevice(); 
    } 
}
