package com.api.crowdfunding.payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.api.crowdfunding.model.porte_project;



public class ProjectResponseForVisitor {
	
	
 
	private Date created_at;
	
	private String nom;    
     
    private String description;    

    private Long montant_minimun;    
   	    
    private Date date_limite_collecte;    
   
    private String contre_partie_project;	      
  
    private String affiche_project; 
 
    private String token;    
   
    private int total_fonds;    
   
    private int total_favoris;    
   
    private int total_hearts;    
  
    private int total_like;    
   
    private int total_dislike;    
    
    private int total_vues;    
 
    private int total_comments;    
   
    private int total_investisseurs;
    
    private CategoryResponse   category_project; 
    
    private porte_project   _porte_project ;    
  
    private StatutResponse  statut_project ;    
   
    private UserResponse  user;
    
    private  List<CommentProjectResponse>   _comments;
    
    private  List<LinkImageProjectResponse>   _links_images;
    
    private  List<NewsProjectResponse>   _news_project;   
    
    private  List<AdressReseauxSociauxProjectResponse>   _adress_sociaux_project; 
    
    
    public ProjectResponseForVisitor(){
    	
    	
    	this._comments = new ArrayList<CommentProjectResponse>();
    	
    	this._links_images = new ArrayList<LinkImageProjectResponse>();
    	
    	this._news_project = new ArrayList<NewsProjectResponse>();
    	
    	this._adress_sociaux_project = new ArrayList<AdressReseauxSociauxProjectResponse>();
    }  
    
    
    

	public Date getCreated_at() {
		return created_at;
	}




	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
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
		return contre_partie_project;
	}

	public void setContrePartieProject(String contrePartieProject) {
		this.contre_partie_project = contrePartieProject;
	}

	public String getAfficheProject() {
		return affiche_project;
	}

	public void setAfficheProject(String afficheProject) {
		this.affiche_project = afficheProject;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getTotal_fonds() {
		return total_fonds;
	}

	public void setTotal_fonds(int total_fonds) {
		this.total_fonds = total_fonds;
	}

	public int getTotal_favoris() {
		return total_favoris;
	}

	public void setTotal_favoris(int total_favoris) {
		this.total_favoris = total_favoris;
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

	public CategoryResponse getCategoryProject() {
		return category_project;
	}

	public void setCategoryProject(CategoryResponse categoryProject) {
		this.category_project = categoryProject;
	}

	public porte_project get_porte_project() {
		return _porte_project;
	}

	public void set_porte_project(porte_project _new_porte_project) {
		this._porte_project = _new_porte_project;
	}

	public StatutResponse get_statut_project() {
		return statut_project;
	}

	public void set_statut_project(StatutResponse _statut_project) {
		this.statut_project = _statut_project;
	}

	public UserResponse get_user() {
		return user;
	}

	public void set_user(UserResponse _user) {
		this.user = _user;
	}


	public List<CommentProjectResponse> get_comments() {
		return _comments;
	}


	public void set_comments(List<CommentProjectResponse> _comments) {
		this._comments = _comments;
	}


	public List<LinkImageProjectResponse> get_links_images() {
		return _links_images;
	}


	public void set_links_images(List<LinkImageProjectResponse> _links_images) {
		this._links_images = _links_images;
	}


	public List<NewsProjectResponse> get_news_project() {
		return _news_project;
	}


	public void set_news_project(List<NewsProjectResponse> _news_project) {
		this._news_project = _news_project;
	}




	public List<AdressReseauxSociauxProjectResponse> get_adress_sociaux_project() {
		return _adress_sociaux_project;
	}




	public void set_adress_sociaux_project(List<AdressReseauxSociauxProjectResponse> _adress_sociaux_project) {
		this._adress_sociaux_project = _adress_sociaux_project;
	}  
	
	
 
    

}

