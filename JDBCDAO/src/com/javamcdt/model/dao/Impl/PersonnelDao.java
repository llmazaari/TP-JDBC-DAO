package com.javamcdt.model.dao.Impl;

import com.javamcdt.domain.Personnel;
import com.javamcdt.model.dao.Dao;
import com.javamcdt.model.ConnexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDao implements Dao<Personnel> {

    private ConnexionDB db;

    public PersonnelDao(ConnexionDB db) {
        this.db = db;
    }

    @Override
    public boolean save(Personnel p) {
        String sql = "INSERT INTO Personnel VALUES (?, ?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, p.getMat());
            ps.setString(2, p.getNom());
            ps.setDouble(3, p.getSalaire());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean saveAll(List<Personnel> liste) {
        for (Personnel p : liste) {
            save(p);
        }
        return true;
    }

    @Override
    public Personnel getById(int id) {
        String sql = "SELECT * FROM Personnel WHERE mat = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Personnel(rs.getInt("mat"), rs.getString("nom"), rs.getDouble("salaire"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Personnel> getAll() {
        List<Personnel> liste = new ArrayList<>();
        String sql = "SELECT * FROM Personnel";
        
        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                liste.add(new Personnel(rs.getInt("mat"), rs.getString("nom"), rs.getDouble("salaire")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return liste;
    }

    @Override
    public boolean update(Personnel p) {
        String sql = "UPDATE Personnel SET nom = ?, salaire = ? WHERE mat = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, p.getNom());
            ps.setDouble(2, p.getSalaire());
            ps.setInt(3, p.getMat());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Personnel WHERE mat = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) { return false; }
    }
}