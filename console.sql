CREATE TABLE test.Users(
                       UserId int primary key ,
                       UserName varchar(255),
                       Password varchar(255),
                       IsAdministrator boolean,
                       Salt varchar(255),
                       Name varchar(255),
                       Surname varchar(255),
                       Birth_Place varchar(255),
                       Status varchar(255)
);
CREATE TABLE test.Friends(
                    SenderId int ,
                    ReceiverId int ,
                    Confirmed boolean,
                    DateSent Date,
                    foreign key (SenderId) references test.Users(UserId),
                    foreign key (ReceiverId) references test.Users(UserId)
);
CREATE TABLE test.Quiz(
                      QuizId int primary key,
                      IsRandom boolean,
                      IsOnePage boolean,
                      IsImmediate boolean,
                      InPracticeMode boolean,
                      NumberOfQuestions int,
                      Description varchar(255),
                      Category varchar(255),
                      CreatorId int,
                      CreateDate Date,
                      foreign key (CreatorId) references test.Users(UserId)
);
CREATE TABLE test.QuizTag(
                      QuizId int ,
                      Tag varchar(255) ,
                      foreign key (QuizId) references test.Quiz(QuizId)
);
CREATE TABLE test.Achievements(
                      UserId int ,
                      Achievement varchar(255) ,
                      foreign key (UserId) references test.Users(UserId)
);
CREATE TABLE test.Questions(
                      QuestionId int primary key ,
                      Question varchar(512) ,
                      Answer varchar(2048) ,
                      Type int ,
                      Score int ,
                      QuizId int ,
                      foreign key (QuizId) references test.Quiz(QuizId)
);
CREATE TABLE test.History(
                      UserId int ,
                      QuizId int ,
                      Score int,
                      StartDate Timestamp, -- Date
                      EndDate Timestamp, -- Date
                      foreign key (UserId) references test.Users(UserId),
                      foreign key (QuizId) references test.Quiz(QuizId)
);

CREATE TABLE test.Mails(
                      MailId int primary key ,
                      SenderId int ,
                      ReceiverId int ,
                      Type varchar(255),
                      Message varchar(255),
                      Date Date,
                      Seen int,
                      foreign key (SenderId) references test.Users(UserId),
                      foreign key (ReceiverId) references test.Users(UserId)
);


