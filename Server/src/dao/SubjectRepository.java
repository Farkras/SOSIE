package dao;

import Models.Eu;
import Models.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository extends DaoBase<Subject> {

    private static final String SElECTREQUEST = "SELECT * FROM Matiere ";
    public static final String TABLENAME = "Matiere";
    public interface Columns{
        String ID       = "MatiereId";
        String EUID     = "UeId";
        String NAME     = "Nom";
        String NBHOURS  = "NombreHeures";
    }

    private static SubjectRepository instance = null;
    public static SubjectRepository getInstance() {
        if(instance == null)
            instance = new SubjectRepository();
        return instance;
    }

    public SubjectRepository() {
        super(TABLENAME, Columns.ID);
    }

    public static Subject getById(int id) {
        ArrayList<Subject> ar = getInstance().select(SElECTREQUEST + "WHERE MatiereId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    public static List<Subject> getByReverseId(Class<?> clazz, int id) {
        String column = null;
        if(clazz == Eu.class) column = "UeId";
        if(column == null) {
            System.err.println(String.format("%s.getByReverseId: Class not found : %s",
                    getInstance().getClass().getSimpleName(), clazz.getClass().getSimpleName()));
            return null;
        }
        return getInstance().select(SElECTREQUEST + "WHERE " + column + " = " + id);
    }

    public static boolean update(Integer id, String columnName, String newValue) {
        return getInstance().updateRow(columnName, newValue, id);
    }

    @Override
    public Subject dataToClass(ResultSet rs) throws SQLException {
        return new Subject(
                rs.getInt(Columns.ID),
                rs.getInt(Columns.EUID),
                rs.getString(Columns.NAME),
                rs.getInt(Columns.NBHOURS)
        );
    }
}
