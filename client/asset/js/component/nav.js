class Nav {

    #topic;
    #currentPage;
    #isLogined;

    constructor(currentPage) {
        this.#currentPage = currentPage;
        let words = currentPage.split('.');
        this.#topic = words[0];
        this.#isLogined = getCookie("token") != null;
    }

    setLoginStatus(isLogined) {
        this.#isLogined = isLogined;
    }

    render() {
        document.getElementsByTagName("header")[0].innerHTML =  `
                <ul class="nav nav-pills">

                <li class="nav-item"><a href="/" class="nav-link ${this.#currentPage == 'index' ? 'active' : ''}">Home</a></li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle ${this.#topic == 'meeting' ? 'active' : ''}" href="#" data-bs-toggle="dropdown" aria-expanded="false">Meeting</a>
                    <ul class="dropdown-menu">
                        <li><a href="/meeting/all.html" class="dropdown-item ${this.#currentPage == 'meeting.all' ? 'active' : ''}">All Meetings</a></li>
                        ${this.#currentPage != 'meeting.detail' ? '' : `
                            <li><hr class="dropdown-divider"></li>
                            <li><a href="#" class="dropdown-item active">Meeting Detail</a></li>
                        `} 
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle ${this.#topic == 'group' ? 'active' : ''}" href="#" data-bs-toggle="dropdown" aria-expanded="false">Meeting Group</a>
                    <ul class="dropdown-menu">
                        <li><a href="/meeting/all" class="dropdown-item ${this.#currentPage == 'group.all' ? 'active' : ''}">All Meeting Groups</a></li>
                        ${this.#currentPage != 'group.new' ? '' : `
                            <li><hr class="dropdown-divider"></li>
                            <li><a href="#" class="dropdown-item active">Create Group</a></li>
                        `} 
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle ${this.#topic == 'participant' ? 'active' : ''}" href="#" data-bs-toggle="dropdown" aria-expanded="false">Participant</a>
                    <ul class="dropdown-menu">
                        <li><a href="/meeting/all" class="dropdown-item">All Participant</a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle ${this.#topic == 'user' ? 'active' : ''}" href="#" data-bs-toggle="dropdown" aria-expanded="false">User</a>
                    <ul class="dropdown-menu">
                        <li><a href="/user/profile.html" class="dropdown-item ${this.#currentPage == 'user.profile' ? 'active' : ''}">Profile</a></li>
                        <li><hr class="dropdown-divider"></li>
                        ${this.#isLogined ? `
                        <li><a href="#" class="dropdown-item">Sign Out</a></li>
                        ` : `
                        <li><a href="/user/login.html" class="dropdown-item ${this.#currentPage == 'user.login' ? 'active' : ''}">Log In</a></li>
                        `} 
                    </ul>
                </li>

                </ul>
            `;
    }

}