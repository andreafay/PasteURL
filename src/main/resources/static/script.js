function showNotification(message) {
    const notification = document.getElementById("notification");
    notification.textContent = message;
    notification.classList.remove("hidden");
    setTimeout(() => {
        notification.classList.add("hidden");
    }, 2000);
}

function showModal() {
    document.getElementById("modal").classList.remove("hidden");
}

function closeModal() {
    document.getElementById("modal").classList.add("hidden");
}

function copyToClipboard(linkElement) {
    const url = linkElement.getAttribute('data-url');
    if (!url) {
        showNotification("Error: No URL found!");
        return;
    }

    navigator.clipboard.writeText(url)
        .then(() => showNotification("Link copied to clipboard!"))
        .catch(err => console.error("Failed to copy:", err));
}

function submitForm(event) {
    event.preventDefault();

    const userEmail = document.getElementById("userEmail").value;
    const linkName = document.getElementById("linkName").value;
    const linkUrl = document.getElementById("linkUrl").value;

    if (!userEmail || !linkName || !linkUrl) {
        showNotification("Please fill in all fields!");
        return;
    }

    fetch("/v1/links/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userEmail: userEmail,
            linkName: linkName,
            linkUrl: linkUrl
        })
    })
    .then(response => response.text())
    .then(data => {
        showNotification("Link saved!");
        closeModal();
        setTimeout(() => location.reload(), 1000);
    })
    .catch(error => {
        console.error("Error adding link:", error);
        showNotification("Failed to add link!");
    });
}

function deleteLink(button) {
    const linkId = button.getAttribute('data-id');

    if (!linkId) {
        showNotification("Error: No ID found for this link!");
        return;
    }

    fetch(`/v1/links/delete?id=${linkId}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(response => {
        if (!response.ok) {
            showNotification("Failed to delete the link!");
            throw new Error("Failed to delete");
        }
        showNotification("Link deleted!");
        setTimeout(() => location.reload(), 1000);
    })
    .catch(error => {
        console.error("Error deleting link:", error);
        showNotification("Failed to delete the link!");
    });
}
