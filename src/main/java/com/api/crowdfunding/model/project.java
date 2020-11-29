package com.api.crowdfunding.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;


import org.hibernate.annotations.ColumnDefault;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;





@Entity
@Table(name = "projects",uniqueConstraints={@UniqueConstraint(columnNames ={"token","nom"})})
@EntityListeners(AuditingEntityListener.class)
public class project  implements Serializable{
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(nullable = false, updatable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    @CreatedDate
	    private Date created_at;

	    @Column(nullable = false)
	    private String nom;
	    
	    @Column(columnDefinition="TEXT",nullable = false)	    
	    private String description;
	    
	    @Column(nullable = false)
	    private Long montant_minimun;
	    
	    @Column(nullable = false)
	    @Temporal(TemporalType.DATE)   	    
	    private Date date_limite_collecte;
	    
	    @Column(nullable = false)
	    private String contrePartieProject;	    
	    
	    @Column(nullable = false)
	    private String afficheProject;    
	  
	    
	    @Column(nullable = false)
	    private String token;
	    
	    @ColumnDefault("0")
	    private int total_fonds;
	    
	    @ColumnDefault("0")
	    private int total_favoris;
	    
	    @ColumnDefault("0")
	    private int total_hearts;
	    
	    @ColumnDefault("0")
	    private int total_like;
	    
	    @ColumnDefault("0")
	    private int total_dislike;
	    
	    @ColumnDefault("0")
	    private int total_vues;
	    
	    @ColumnDefault("0")
	    private int total_comments;
	    
	    @ColumnDefault("0")
	    private int total_investisseurs;
	    
	

	    @OneToOne
        @JoinColumn(name = "id_category", referencedColumnName = "id")
        private category_project categoryProject;  
	    
	    
	    

	    @OneToOne
        @JoinColumn(name = "porte_project_id", referencedColumnName = "id")
        private porte_project  _porte_project ;
	    
	    @OneToOne
	    @ColumnDefault("1")
        @JoinColumn(name = "statut_project", referencedColumnName = "id")
        private statutProject _statut_project ;
	    
	    @OneToOne
        @JoinColumn(name = "token_user", referencedColumnName = "token")
        private User _user;
	    
	    
	 
	    @OneToOne
        @JoinColumn(name = "token_manager", referencedColumnName = "token")
        private User manager_project ;
	    

	    
	    
	    

		public User getManager_project() {
			return manager_project;
		}

		public void setManager_project(User manager_project) {
			this.manager_project = manager_project;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Long getMontant_minimun() {
			return montant_minimun;
		}

		public void setMontant_minimun(Long montant_minimun) {
			this.montant_minimun = montant_minimun;
		}

		public Date getDate_limite_collecte() {
			return date_limite_collecte;
		}

		public void setDate_limite_collecte(Date date_limite_collecte) {
			this.date_limite_collecte = date_limite_collecte;
		}

		public String getContrePartieProject() {
			return contrePartieProject;
		}

		public void setContrePartieProject(String contrePartieProject) {
			this.contrePartieProject = contrePartieProject;
		}

		public String getAfficheProject() {
			return afficheProject;
		}

		public void setAfficheProject(String afficheProject) {
			this.afficheProject = afficheProject;
		}
		

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public category_project getCategoryProject() {
			return categoryProject;
		}

		public void setCategoryProject(category_project categoryProject) {
			this.categoryProject = categoryProject;
		}

		public porte_project get_porte_project() {
			return _porte_project;
		}

		public void set_porte_project(porte_project porte_project) {
			this._porte_project = porte_project;
		}

		public statutProject get_statut_project() {
			return _statut_project;
		}

		public void set_statut_project(statutProject _statut_project) {
			this._statut_project = _statut_project;
		}

		public User get_user() {
			return _user;
		}

		public void set_user(User _user) {
			this._user = _user;
		}

		public int getTotal_fonds() {
			return total_fonds;
		}

		public void setTotal_fonds(int totalFonds) {
			this.total_fonds = totalFonds;
		}
		
	    public int getTotal_hearts() {
				return total_hearts;
		}

		public void setTotal_hearts(int total_hearts) {
				this.total_hearts = total_hearts;
		}
	

		public int getTotal_like() {
			return total_like;
		}

		public void setTotal_like(int total_like) {
			this.total_like = total_like;
		}

		public int getTotal_dislike() {
			return total_dislike;
		}

		public void setTotal_dislike(int total_dislike) {
			this.total_dislike = total_dislike;
		}

		public int getTotal_vues() {
			return total_vues;
		}

		public void setTotal_vues(int total_vues) {
			this.total_vues = total_vues;
		}

		public Date getCreated_at() {
			return created_at;
		}

		public void setCreated_at(Date created_at) {
			this.created_at = created_at;
		}

		public int getTotal_comments() {
			return total_comments;
		}

		public void setTotal_comments(int total_comments) {
			this.total_comments = total_comments;
		}

		public int getTotal_investisseurs() {
			return total_investisseurs;
		}

		public void setTotal_investisseurs(int total_investisseurs) {
			this.total_investisseurs = total_investisseurs;
		}

		public int getTotal_favoris() {
			return total_favoris;
		}

		public void setTotal_favoris(int total_favoris) {
			this.total_favoris = total_favoris;
		}

		@Override
		public String toString() {
			return "project [id=" + id + ", created_at=" + created_at + ", nom=" + nom + ", description=" + description
					+ ", montant_minimun=" + montant_minimun + ", date_limite_collecte=" + date_limite_collecte
					+ ", contrePartieProject=" + contrePartieProject + ", afficheProject=" + afficheProject + ", token="
					+ token + ", total_fonds=" + total_fonds + ", total_hearts=" + total_hearts + ", total_like="
					+ total_like + ", total_dislike=" + total_dislike + ", total_vues=" + total_vues
					+ ", total_comments=" + total_comments + ", total_investisseurs=" + total_investisseurs
					+ ", categoryProject=" + categoryProject + ", _porte_project=" + _porte_project
					+ ", _statut_project=" + _statut_project + ", _user=" + _user + ", manager_project="
					+ manager_project + "]";
		}   
        
	     
	
	  

}
