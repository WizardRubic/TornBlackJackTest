// makeAPICall()
// Desc: keep making api calls til a response is received
function makeAPICall()
{
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() { 
        if (4 == xhr.readyState && 200 == xhr.status) {
            handleAPICall(xhr.responseText);
        }
    }
    xhr.open("GET", "https://api.torn.com/user/1643506?selections=&key=SFfDBKRg", true); 
    xhr.send(null);
}

// handleAPICall()
// Desc: handler that will check if the necessary conditions for spawning a notification are met.
function handleAPICall(s) {
	// Get the last action text
	var lastAction = JSON.parse(s)["last_action"];

	// Figure out what scale the last action time is on
	var regex = /(minute | minutes |hour |hours |day |days )/g;
	var timespan = regex.exec(s)[0];
	console.log(timespan);

	// 

}



var online = 1; // Whether the target is online
var badgeObject = new Object(); // Badge info
badgeObject.text = "1";
chrome.browserAction.setBadgeText(badgeObject);
setInterval(makeAPICall,2000);


