DROP DATABASE restapidb;

GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY 'homedb' WITH GRANT OPTION;

CREATE DATABASE restapidb;

CREATE TABLE UserStats(userStatsId INT PRIMARY KEY AUTO_INCREMENT, noOfRatings INT, totalRatings INT, totalReviews INT, noOfUsefulReviews INT, authenticityIndex FLOAT, lastLoginDate TIMESTAMP, noOfSuccessfulLogins INT, lastUpdatedDate TIMESTAMP, createdAt TIMESTAMP);



UserDetail		userId	userEmailId	userPassword	userName	contactNumber	userStatsId	location	pinCode	lastUpdatedDate	createdDate					
																
UserStats		userStatsId	noOfRatings	totalRatings	totalReviews	noOfUsefulReviews	authenticityIndex	lastLoginDate	noOfSuccessfulLogins	lastUpdatedDate	createdDate					
																
DoctorDetail		doctorId	doctorEmailId	doctorPassword	doctorName	contactNumber	profileId	totalExperience	doctorStatsId	primarySpecializationId	LIST<Specialization>	primaryLocation	pinCode	LIST<Address>	lastUpdatedDate	createdDate
																
DoctorProfile		profileId	uniqueId	LIST<Achievement>	LIST<Experience>	LIST<Education>	lastUpdatedDate	createdDate								
																
DoctorStats		doctorStatsId	noOfRatings	totalRatings	totalReplies	noOfAcceptedReplies	credibilityIndex	lastLoginDate	noOfSuccessfulLogins	lastUpdatedDate	createdDate					
																
Rating		ratingId	userId	doctorId	ratedPoints	reviewId	createdDate									
																
Review		reviewId	reviewText	reviewStatusId	noOfUpVotes	LIST<COMMENT>	createdDate									
																
COMMENT		commentId	commenterEmailId	commentText	commentStatusId	noOfUpVotes	createdDate									
																
Specialization		specializationId	specialization	specializationDesc												
																
Achievement		achievementId	achievementDesc	place	achievedFrom	achievedDate	createdDate									
																
Experience		experienceId	experienceDesc	institution	fromDate	toDate	createdDate									
																
Education		educationId	educationDesc	degree	fromDate	toDate	createdDate									
																
Address		addressId	address	landmark	location	pinCode	city	country	createdDate							
																
ReviewStatus		reviewStatusId	reviewStatus	reviewStatusDesc												
																
CommentStatus		commentStatusId	commentStatus	commentStatusDesc												
																
DoctorSpecializations		doctorId	specializationId	createdDate												
																
DoctorAddresses		doctorId	addressId	createdDate												
																
DoctorAchievements		profileId	achievementId	createdDate												
																
DoctorExperiences		profileId	experienceId	createdDate												
																
DoctorEducations		profileId	educationId	createdDate												
																
ReviewComments		reviewId	commentId	createdDate												






COMMIT;

SELECT * FROM userdata;

