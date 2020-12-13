package com.api.crowdfunding.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import com.api.crowdfunding.enumapp.likeDislikeProject;
import com.api.crowdfunding.model.QuestionAidesProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsDislikeProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsHeartsProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsLikeProjectModel;
import com.api.crowdfunding.model.StatistiquesChartsVueProjectModel;
import com.api.crowdfunding.model.category_project;
import com.api.crowdfunding.model.favorisProjectUserModel;
import com.api.crowdfunding.model.fondInvestorModel;
import com.api.crowdfunding.model.heartProjectModel;
import com.api.crowdfunding.model.investisseursProjectModel;
import com.api.crowdfunding.model.likeDislikeProjectModel;
import com.api.crowdfunding.model.newsProjectModel;
import com.api.crowdfunding.model.porte_project;
import com.api.crowdfunding.model.project;
import com.api.crowdfunding.model.statutProject;
import com.api.crowdfunding.model.vueProjectModel;
import com.api.crowdfunding.payload.AddFondProjectRequest;
import com.api.crowdfunding.payload.AdressReseauxSociauxProjectRequest;
import com.api.crowdfunding.payload.AdressReseauxSociauxProjectResponse;
import com.api.crowdfunding.payload.CommentProjectResponse;
import com.api.crowdfunding.payload.CommentRequest;
import com.api.crowdfunding.payload.CommissionProjectRequest;
import com.api.crowdfunding.payload.DemandeInvestisRequest;
import com.api.crowdfunding.payload.LinkImageProjectResponse;
import com.api.crowdfunding.payload.LinkImageRequest;
import com.api.crowdfunding.payload.NewProjectRequest;
import com.api.crowdfunding.payload.ProjectResponseForVisitor;
import com.api.crowdfunding.payload.ProjetRequest;
import com.api.crowdfunding.payload.QuestionAideRequest;
import com.api.crowdfunding.payload.TypeStatistiqeRequest;
import com.api.crowdfunding.payload.VueProjectRequest;
import com.api.crowdfunding.security.CurrentUser;
import com.api.crowdfunding.security.UserPrincipal;

public interface ProjectService {

	
	List<project> getAllProjectsByCurrentUser(@CurrentUser UserPrincipal currentUser);
	
	List<project> getMyProjectsByCurrentUser(@CurrentUser UserPrincipal currentUser);
	
	List<project> getAllProjectsByUser(@CurrentUser UserPrincipal currentUser, String token_user);
	
	List<project> getAllContribProjectsByUser(@CurrentUser UserPrincipal currentUser, String token_user);
	
	project getInfosMyProjectByCurrentUser(@CurrentUser UserPrincipal currentUser, String tokenProject);
	
	project getInfosProjectByOtherUser(@CurrentUser UserPrincipal currentUser, String tokenProject);
	
	project createProject(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody ProjetRequest newProject);
	
	project updateProject(@CurrentUser UserPrincipal currentUser, String token_project,@Valid @RequestBody ProjetRequest updateProject);
	
	project updateProjectByManagerProject(@CurrentUser UserPrincipal currentUser, String token_project,String token_statut);
	
	Boolean checkCommissionProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	Boolean addCommissionProject(@CurrentUser UserPrincipal currentUser,String token_project,  @Valid @RequestBody  CommissionProjectRequest   _commissionProjectRequest);
	
	List<AdressReseauxSociauxProjectResponse> getlistAdressSociauxByProject(String token_project);
	
	AdressReseauxSociauxProjectResponse createAdressReseauSocialProject(@CurrentUser UserPrincipal currentUser,String token_project, @Valid @RequestBody AdressReseauxSociauxProjectRequest _linkSocialReq);
	
	Boolean deleteAdressReseauSocialProject(@CurrentUser UserPrincipal currentUser, String token_project,String token_address);
	
	List<LinkImageProjectResponse> getlistLinksImagesByProject(String token_project);
	
	LinkImageProjectResponse createLinkImageProject(@CurrentUser UserPrincipal currentUser, String token_project,@Valid @RequestBody LinkImageRequest _linkImageReq);
	
	Boolean deleteLinkImageProject(@CurrentUser UserPrincipal currentUser, String token_project, String token_image);
	
	List<CommentProjectResponse> getlistCommentsByProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	CommentProjectResponse createCommentProject(@CurrentUser UserPrincipal currentUser, String token_project,@Valid @RequestBody CommentRequest _comment);
	
	QuestionAidesProjectModel createQuestionAideByProject(@CurrentUser UserPrincipal currentUser,String token_project, @Valid @RequestBody QuestionAideRequest _questionProject);
	
	List<QuestionAidesProjectModel> getListQuestionsAidesByProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	investisseursProjectModel createDemandeInvestProject(@CurrentUser UserPrincipal currentUser,String token_project);
	
	investisseursProjectModel updateDemandeInvestProject(@CurrentUser UserPrincipal currentUser, String token_project, String token_demande, DemandeInvestisRequest _demandeInvest);
	
	Boolean checkInvestProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	investisseursProjectModel getInfosInvestProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	List<investisseursProjectModel> getlistMyProjectsInvest(@CurrentUser UserPrincipal currentUser);
	
	List<investisseursProjectModel> getlistInvestisseursByProjectForUser(@CurrentUser UserPrincipal currentUser,String token_project);
	
	List<investisseursProjectModel> getlistInvestisseursByProjectForPorteurProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	fondInvestorModel createFondInvestProject(UserPrincipal currentUser, String token_project,String token_demande, @Valid AddFondProjectRequest _fondInvest);
	
	List<fondInvestorModel> getListFondInvestByProjectForPorteurProject(@CurrentUser UserPrincipal currentUser,String token_project);
	
	List<fondInvestorModel> getListFondInvestByProjectForInvestisseurProject(@CurrentUser UserPrincipal currentUser,String token_project);
	
	favorisProjectUserModel createFavorisProject(UserPrincipal currentUser, String token_project);
	
	Boolean deleteFavorisProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	Boolean checkFavorisProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	List<favorisProjectUserModel> getListFavorisProjects(@CurrentUser UserPrincipal currentUser);
	
	heartProjectModel createHeartProject(UserPrincipal currentUser, String token_project);
	
	Boolean deleteHeartProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	List<heartProjectModel> getListHeartsProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	Boolean checkHeartProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	likeDislikeProjectModel createLike_Dislike_Project(UserPrincipal currentUser, String token_project,likeDislikeProject _like_dislike);
	
	Boolean deleteLikeDislikeProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	likeDislikeProjectModel checkLikeDislikeProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	likeDislikeProjectModel updateLike_Dislike_Project(UserPrincipal currentUser, String token_project,likeDislikeProject _like_dislike);
	
	List<likeDislikeProjectModel> getListLikesDislikesProject(@CurrentUser UserPrincipal currentUser,String token_project);
	
	vueProjectModel createOrUpdateVueProject(UserPrincipal currentUser, String token_project,VueProjectRequest _vueProject);
	
	newsProjectModel createNewProject(UserPrincipal currentUser, String token_project,NewProjectRequest _newProject);
	
	newsProjectModel updateNewProject(UserPrincipal currentUser, String token_project, String token_news,NewProjectRequest _newProject);
	
	Boolean deleteNewProject(UserPrincipal currentUser, String token_project, String token_news);
	
	List<newsProjectModel> getListNewsProject(@CurrentUser UserPrincipal currentUser, String token_project);
	
	List<StatistiquesChartsHeartsProjectModel> getListStatistiquesHeartsProject(@CurrentUser UserPrincipal currentUser, String token_project, TypeStatistiqeRequest _type_stat);
	
	List<StatistiquesChartsVueProjectModel> getListStatistiquesVuesProject(@CurrentUser UserPrincipal currentUser, String token_project, TypeStatistiqeRequest _type_stat);
	
	List<StatistiquesChartsLikeProjectModel> getListStatistiquesLikesProject(@CurrentUser UserPrincipal currentUser, String token_project, TypeStatistiqeRequest _type_stat);
	
	List<StatistiquesChartsDislikeProjectModel> getListStatistiquesDisLikesProject(@CurrentUser UserPrincipal currentUser, String token_project, TypeStatistiqeRequest _type_stat);
	
	List<category_project> getListCategoriesProject();
	
	List<category_project> getListCustomCategoriesProject();
	
	List<porte_project> getListPortesProject();
	
	List<statutProject> getListStatusProject();
	
	List<ProjectResponseForVisitor> findAllProjectsByLikeTag(String tag);
	
	List<ProjectResponseForVisitor> getAllProjectsByCategory(String nom_category);
	
	List<ProjectResponseForVisitor> getRandomProjectsByCategory(String nom_category);
	
	List<ProjectResponseForVisitor> getAllProjectsTopConsulte();
	
	List<ProjectResponseForVisitor> getAllProjectsTopHearts();
	
	List<ProjectResponseForVisitor> getAllProjectsTopLike();
	
	ProjectResponseForVisitor  getRandomProject();
	
	ProjectResponseForVisitor  getInfosProjectForVisitor(String token_project);
	
	
	

	

}
