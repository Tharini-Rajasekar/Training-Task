const wrapper=document.querySelector('.wrapper');
const loginLink=document.querySelector('.signin-link');
const registerLink=document.querySelector('.signup-link');
const btnPopup=document.querySelector('.btnLogin-popup');

registerLink.addEventListener('click',()=>{
	wrapper.classList.add('active');
});

loginLink.addEventListener('click',()=>{
	wrapper.classList.remove('active');
});

btnPopup.addEventListener('click',()=>{
	wrapper.classList.add('active-popup');
});



  
function toggleSection(sectionId) {
   
    var sections = document.querySelectorAll('.content > .container > div');
    sections.forEach(function(section) {
        section.classList.add('hidden');
    });

   
    var selectedSection = document.getElementById(sectionId);
    selectedSection.classList.remove('hidden');
}


 document.getElementById("userSwitch").addEventListener("click", function() {
        var userLevel = document.getElementById("userLevel").value;
        if (userLevel === "1") {
            toggleSection('newCustomer');
        } else {
            toggleSection('newEmployee');
        }
    });
    
    
    document.getElementById("userLevelDetails").addEventListener("change", function() {
    var userLevel = this.value;
    var customerFields = document.getElementById("customerFields");
    var employeeFields = document.getElementById("employeeFields");

   
    customerFields.classList.add("hidden");
    employeeFields.classList.add("hidden");

    
    if (userLevelDetails === "1") {
        customerFields.classList.remove("hidden");
    } else if (userLevelDetails === "2" || userLevelDetails === "3") {
        employeeFields.classList.remove("hidden");
    }
});

 document.getElementById("editUser").addEventListener("click", function() {
        var userLevel = document.getElementById("userLevel").value;
        if (userLevel === "1") {
            toggleSection('updateCustomer');
        }
         else {
            toggleSection('updateEmployee');
        }
    });
    