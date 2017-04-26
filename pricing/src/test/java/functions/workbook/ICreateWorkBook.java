package functions.workbook;

import setup.IPage;

public interface ICreateWorkBook extends IPage{
    public void setName(String name);
    public void setDescription(String description);
    public void setFormulaType(String formulaType);
    public void setSegmentType(String segmentType);
    public void hasDefaultValue(boolean hasDefaultValue);
    public void setDefaultValue(String defaultValue);
    public void addSingleAttribte();
    public void addMultipleAttribte();
    public void removeSingleAttribte();
    public void removeMultipleAttribte();
    public void selectAttribute(String attribute);
    public void selectMultipleAttributes(String[] attributes);
}
