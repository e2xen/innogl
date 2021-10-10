import React, {useMemo} from "react";
import './Privacy.css';

function Privacy() {
    document.title = useMemo(() => {
        return 'Privacy and Policy - Innogl'
    }, []);

    return (
        <div className="parag">
            <h2 className="parag"> Privacy and policy </h2>
            <p className="pepe">
                This Privacy Policy covers our treatment of personally identifiable information (“Personal Information”)
                that we gather when you are accessing or using our Services or Site, but not to the practices of
                companies we don’t own or control, or people that we don’t manage. and we use this Personal Information
                in connection with our Services and Site including to personalize, provide, and improve our Services, to
                allow you to contact you and allow other users to contact you, to fulfill your requests for certain
                products and services, and to analyze how you use the Services. In certain cases, we may also share some
                Personal Information with third parties, but only as described below. As noted in the Terms of Use, we
                do not knowingly collect or solicit personal information from anyone under the age of 13. If you are
                under 13, please do not attempt to register for the Services or send any personal information about
                yourself to us. If we learn that we have collected personal information from a child under age 13, we
                will delete that information as quickly as possible. If you believe that a child under 13 may have
                provided us personal information, please contact us.
                <br/>
                This policy applies only to information we collect through the Site, Services, Apps and any emails,
                texts or other electronic communications sent through the Site. This policy DOES NOT apply to
                information collected offline, through any other apps or websites (including websites you may access
                through the Site) or by any third party, including through any application or content (including
                advertising) that may link to or be accessible from or on the Site. These other websites, apps or third
                parties may have their own privacy policies, which we encourage you to read before providing information
                through them.
                <br/>
                If you do not agree with this Privacy Policy, do not access or use the Site, Services and/or Apps. By
                accessing or using the Site, Services and/or Apps you agree to this Privacy Policy.
                <br/>
                You can always opt not to disclose information to us, but keep in mind some information may be needed to
                register with us, or to take advantage of some of our features. You may stop using the Services at any
                time; however, your conversation logs from communications may remain in our records if it is necessary
                in relation to the contract entered into with you and in relation to the Client´s business.
                <br/>
                <b>In addition: we do not use your personal information. Your IP address in hidden to your
                    companion, and we don't use or store it anywhere.
                    Each chat is secured and has token-protection access. Token is unique for each session.</b>
            </p>
        </div>
    );
}

export default Privacy;