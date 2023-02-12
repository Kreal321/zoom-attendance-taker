class IconCard {

    #title;
    #info;
    #footer;
    #iconClass;
    #footerStyle;

    constructor(title, info, footer, iconClass, footerStyle="") {
        this.#title = title;
        this.#info = info;
        this.#footer = footer;
        this.#iconClass = iconClass;
        this.#footerStyle = footerStyle;
    }

    render() {
        return `
            <div class="col-md-6 mb-3">
                <div class="card">
                <div class="card-body p-3">
                    <div class="d-flex justify-content-between">
                    <div>
                        <p class="fs-6 mb-0 text-uppercase">${this.#title}</p>
                        <h5 class="fs-4 fw-bolder mb-0">${this.#info}</h5>
                    </div>
                    <div class="icon icon-shape bg-primary rounded-circle text-center">
                        <i class="bi bi-${this.#iconClass} text-white"></i>
                    </div>
                    </div>
                    <p class="mb-0 fs-6 fw-light ${this.#footerStyle}">
                        ${this.#footer}
                    </p>
                </div>
                </div>
            </div>

        
        `;
    }
}