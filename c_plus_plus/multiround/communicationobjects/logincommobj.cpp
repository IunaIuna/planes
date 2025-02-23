#include "logincommobj.h"


#include <QMessageBox>
#include "viewmodels/loginviewmodel.h"
#include "communicationtools.h"

LoginCommObj::~LoginCommObj()
{
    delete m_LoadingMessageBox;
}


bool LoginCommObj::makeRequest(const QString& username, const QString& password)
{
    LoginViewModel loginData;
    loginData.m_Password = password; 
    loginData.m_UserName = username; 

    m_GlobalData->reset();
    m_UserName = username;
    m_RequestData = loginData.toLoginJson();
    
    m_LoadingMessageBox->show();
    
    makeRequestBasis(false);
    return true;
}

void LoginCommObj::finishedRequest()
{
    if (m_LoadingMessageBox->isVisible())
        m_LoadingMessageBox->hide();

    QJsonObject retJson;
    if (!finishRequestHelper(retJson)) 
        return;
    
    QList<QByteArray> headers = m_ReplyObject->rawHeaderList();
    bool successfull = false;
    
    for(QByteArray hdr : headers) {
        QString hdrQString(hdr);
        if (hdrQString == "Authorization") {
            m_GlobalData->m_UserData.m_AuthToken = m_ReplyObject->rawHeader(hdr);
            //qDebug() << hdrQString << ":" << m_ReplyObject->rawHeader(hdr);
            successfull = true;
        }
    }
    
    //TODO: token refresh
    if (successfull) {
        QMessageBox msgBox(m_ParentWidget);
        msgBox.setText("Login successfull!"); 
        msgBox.exec();                       
        m_GlobalData->m_UserData.m_UserName = retJson.value("username").toString();
        m_GlobalData->m_UserData.m_UserId = retJson.value("id").toString().toLong();
        m_GlobalData->m_UserData.m_UserPassword.clear();
        m_GlobalData->m_GameData.reset();
        //qDebug() << "Login successfull " << m_GlobalData->m_UserData.m_UserName << " " << m_GlobalData->m_UserData.m_UserId;
        emit loginCompleted();
    } else {
        QMessageBox msgBox(m_ParentWidget);
        msgBox.setText("Login reply was not recognized"); 
        msgBox.exec();        
    }
}

bool LoginCommObj::validateReply(const QJsonObject& reply) {
    if (!reply.contains("id") || !reply.contains("username"))
        return false;

    if (!checkLong(reply.value("id").toString()))
        return false;
    
    return true;
}

void LoginCommObj::errorRequest(QNetworkReply::NetworkError code)
{
    if (m_LoadingMessageBox->isVisible())
        m_LoadingMessageBox->hide();
    BasisCommObj::errorRequest(code);
    emit loginFailed();
}
